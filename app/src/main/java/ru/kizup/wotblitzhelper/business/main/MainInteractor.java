package ru.kizup.wotblitzhelper.business.main;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.realm.RealmList;
import okhttp3.internal.http.HttpCodec;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.InternetUnavailableException;
import ru.kizup.wotblitzhelper.data.repositories.main.IMainRepository;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;
import ru.kizup.wotblitzhelper.models.common_info.AchievementDao;
import ru.kizup.wotblitzhelper.models.common_info.AchievementSectionDataModel;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.models.common_info.Language;
import ru.kizup.wotblitzhelper.models.common_info.VehicleNationDao;
import ru.kizup.wotblitzhelper.models.common_info.VehicleTypeDao;
import ru.kizup.wotblitzhelper.models.view_vehicle.ArmorDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModuleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModulesDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.NextTank;
import ru.kizup.wotblitzhelper.models.view_vehicle.PriceXp;
import ru.kizup.wotblitzhelper.models.view_vehicle.ProfileDataModel;
import ru.kizup.wotblitzhelper.utils.network.NetworkUtil;

/**
 * Created by: dpuzikov on 10.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class MainInteractor
        implements IMainInteractor {

    private IMainRepository mMainRepository;
    private ModulesDataModel modulesDataModel;
    private Context mContext;

    public MainInteractor(IMainRepository mainRepository, Context context) {
        mMainRepository = mainRepository;
        mContext = context;
    }

    @Override
    public Single<Boolean> updateDatabase() {
        return Single.zip(loadAllVehicleShortInfoAndCache(),
                loadAllVehicleDetailInfoAndCache(), loadVehicleModulesAndCache(),
                (shortLoadResult, detailLoadResult, modulesLoadResult) ->
                        shortLoadResult && detailLoadResult && modulesLoadResult)
                .flatMap(result -> loadAllAchievementsAndCache())
                .flatMap(result -> loadAllCrewSkillsAndCache())
                .flatMap(result -> loadCommonWikiInfoAndCache());
    }

    @Override
    public Single<Boolean> checkDatabaseUpdated() {
        return mMainRepository.getCommonInfoFromDatabase()
                .flatMap(dataModel -> {
                    if (NetworkUtil.isNetworkAvailable(mContext)) {
                        return Single.zip(Single.just(dataModel),
                                mMainRepository.getGameVersionFromServer()
                                        .compose(responseTransformer()),
                                (dbModel, networkModel) -> dbModel.getGameVersion().equals(networkModel.getGameVersion()));
                    }
                    return Single.just(true);
                });
    }

    private Single<Boolean> loadAllVehicleShortInfoAndCache() {
        return mMainRepository.getAllVehiclesFromServer()
                .compose(responseTransformer())
                .flatMapObservable(vehiclesMap -> Observable.fromIterable(vehiclesMap.values()))
                .doOnNext(shortDataModel -> mMainRepository.saveModel(shortDataModel).subscribe())
                .toList()
                .map(list -> true);
    }

    private Single<Boolean> loadAllVehicleDetailInfoAndCache() {
        return mMainRepository.getAllVehiclesDetailInfoFromServer()
                .compose(responseTransformer())
                .flatMapObservable(detailMap -> Observable.fromIterable(detailMap.values()))
                .doOnNext(this::prepareModelAndSaveToDatabase)
                .toList()
                .map(list -> true);
    }

    private Single<Boolean> loadVehicleModulesAndCache() {
        return mMainRepository.getAllModulesForVehicleFromServer()
                .compose(responseTransformer())
                .doOnSuccess(modules -> modulesDataModel = modules)
                .flatMapObservable(modules -> Observable.fromIterable(modules.getEngines()))
                .doOnNext(module -> mMainRepository.saveModel(module).subscribe())
                .toList()
                .flatMapObservable(o -> Observable.fromIterable(modulesDataModel.getGuns()))
                .doOnNext(module -> mMainRepository.saveModel(module).subscribe())
                .toList()
                .flatMapObservable(o -> Observable.fromIterable(modulesDataModel.getSuspensions()))
                .doOnNext(module -> mMainRepository.saveModel(module).subscribe())
                .toList()
                .flatMapObservable(o -> Observable.fromIterable(modulesDataModel.getTurrets()))
                .doOnNext(module -> mMainRepository.saveModel(module).subscribe())
                .toList()
                .map(model -> true);
    }

    private Single<Boolean> loadAllAchievementsAndCache() {
        return mMainRepository.getAllAchievementsFromServer()
                .compose(responseTransformer())
                .map(this::prepareAchievementsForSaveToDatabase)
                .flatMapObservable(Observable::fromIterable)
                .flatMapSingle(dao -> mMainRepository.saveModel(dao))
                .toList()
                .map(list -> true);
    }

    private Single<Boolean> loadAllCrewSkillsAndCache() {
        return mMainRepository.getAllCrewSkillsFromServer()
                .compose(responseTransformer())
                .flatMapObservable(crewSkillsMap -> Observable.fromIterable(crewSkillsMap.values()))
                .flatMapSingle(model -> mMainRepository.saveModel(model))
                .toList()
                .map(list -> true);
    }

    private Single<Boolean> loadCommonWikiInfoAndCache() {
        return mMainRepository.getCommonWikiInfoFromServer()
                .compose(responseTransformer())
                .map(this::prepareCommonInfoForSaveToDatabase)
                .flatMap(model -> mMainRepository.saveModel(model));
    }

    private <D> SingleTransformer<Response<BaseResponse<D>>, D> responseTransformer() {
        return upstream -> upstream
                .map(response -> {
                    if (!NetworkUtil.isNetworkAvailable(mContext)) {
                        throw new InternetUnavailableException();
                    }
                    switch (response.code()) {
                        case 500: {
                            throw new FailureResponseException();
                        }
                    }
                    return response;
                })
                .map(Response::body)
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(response.getError());
                    }
                    return response;
                })
                .map(BaseResponse::getData);
    }

    private List<AchievementDao> prepareAchievementsForSaveToDatabase(HashMap<String, AchievementsModel> achievementsModelMap) {
        List<AchievementDao> achievementDaos = new ArrayList<>();
        for (String key : achievementsModelMap.keySet()) {
            achievementDaos.add(new AchievementDao(key, achievementsModelMap.get(key)));
        }
        return achievementDaos;
    }

    private CommonInfoDataModel prepareCommonInfoForSaveToDatabase(CommonInfoDataModel dataModel) {
        if (dataModel.getAchievementSections() != null) {
            RealmList<AchievementSectionDataModel> achievementSectionDataModels = new RealmList<>();
            for (String key : dataModel.getAchievementSections().keySet()) {
                AchievementSectionDataModel model = dataModel.getAchievementSections().get(key);
                model.setCode(key);
                achievementSectionDataModels.add(model);
            }
            dataModel.setAchievementsSectionsList(achievementSectionDataModels);
        }

        if (dataModel.getLanguages() != null) {
            RealmList<Language> languages = new RealmList<>();
            for (String key : dataModel.getLanguages().keySet()) {
                languages.add(new Language(key, dataModel.getLanguages().get(key)));
            }
            dataModel.setLanguagesList(languages);
        }

        if (dataModel.getVehicleNations() != null) {
            RealmList<VehicleNationDao> vehicleNationDaos = new RealmList<>();
            for (String key : dataModel.getVehicleNations().keySet()) {
                vehicleNationDaos.add(new VehicleNationDao(key, dataModel.getVehicleNations().get(key)));
            }
            dataModel.setVehicleNationsList(vehicleNationDaos);
        }

        if (dataModel.getVehicleTypes() != null) {
            RealmList<VehicleTypeDao> vehicleTypeDaos = new RealmList<>();
            for (String key : dataModel.getVehicleTypes().keySet()) {
                vehicleTypeDaos.add(new VehicleTypeDao(key, dataModel.getVehicleTypes().get(key)));
            }
            dataModel.setVehicleTypesList(vehicleTypeDaos);
        }
        return dataModel;
    }

    private void prepareModelAndSaveToDatabase(DetailVehicleDataModel model) {
        if (model.getPricesXp() != null) {
            RealmList<PriceXp> prices = new RealmList<>();
            for (String key : model.getPricesXp().keySet()) {
                prices.add(new PriceXp(key, model.getPricesXp().get(key)));
            }
            model.setPrices(prices);
        }

        if (model.getNextTanks() != null) {
            RealmList<NextTank> nextTanks = new RealmList<>();
            for (String key : model.getNextTanks().keySet()) {
                nextTanks.add(new NextTank(key, model.getNextTanks().get(key)));
            }
            model.setNextTanksList(nextTanks);
        }

        if (model.getModules() != null) {
            RealmList<ModuleDataModel> modules = new RealmList<>();
            for (String key : model.getModules().keySet()) {
                modules.add(model.getModules().get(key));
            }
            model.setModulesList(modules);
        }

        ProfileDataModel vehicleProfile = model.getDefaultProfile();

        if (vehicleProfile != null) {
            vehicleProfile.getSuspension().setId(vehicleProfile.getSuspensionId());
            vehicleProfile.getEngine().setId(vehicleProfile.getEngineId());
            if (vehicleProfile.getTurretId() != null)
                vehicleProfile.getTurret().setId(vehicleProfile.getTurretId());
            vehicleProfile.getGun().setId(vehicleProfile.getGunId());

            if (vehicleProfile.getArmor() != null) {
                RealmList armorList = new RealmList();
                for (String key : vehicleProfile.getArmor().keySet()) {
                    ArmorDataModel armorDataModel = vehicleProfile.getArmor().get(key);
                    if (key.equals(ArmorDataModel.HULL)) {
                        armorDataModel.setType(ArmorDataModel.HULL_ARMOR);
                    }

                    if (key.equals(ArmorDataModel.TURRET)) {
                        armorDataModel.setType(ArmorDataModel.TURRET_ARMOR);
                    }

                    armorList.add(armorDataModel);
                }
                vehicleProfile.setArmorsList(armorList);
            }
        }

        mMainRepository.saveModel(model).subscribe();
    }

}
