package ru.kizup.wotblitzhelper.business.view_vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.realm.RealmList;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.IVehiclesRepository;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ArmorDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.EngineDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.GunDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.GunUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModuleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModuleUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModulesDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.NextTank;
import ru.kizup.wotblitzhelper.models.view_vehicle.PriceXp;
import ru.kizup.wotblitzhelper.models.view_vehicle.ProfileDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ProfileUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.SuspensionDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.TurretDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.VehicleModule;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ViewVehicleInteractor
        implements IViewVehicleInteractor {

    private IVehiclesRepository mVehiclesRepository;

    public ViewVehicleInteractor(IVehiclesRepository vehiclesRepository) {
        mVehiclesRepository = vehiclesRepository;
    }

    @Override
    public Single<DetailVehicleUIModel> getVehicleDetailInformation(int id) {
        return getVehicleDetailDataModel(id)
                .map(this::mapDataModel);
    }

    @Override
    public Single<List<ShortVehicleInfoUIModel>> getNextTanksShortInfo(List<NextTank> nextTanks) {
        Integer[] ids = new Integer[nextTanks == null ? 0 : nextTanks.size()];
        if (nextTanks != null && !nextTanks.isEmpty()) {
            for (int i = 0; i < nextTanks.size(); i++) {
                ids[i] = Integer.parseInt(nextTanks.get(i).getNextTankId());
            }
        }
        return mVehiclesRepository.getNextVehiclesShortInfo(ids)
                .flatMapObservable(Observable::fromIterable)
                .map(this::mapToUIModel)
                .toList();
    }

    @Override
    public Single<List<? extends VehicleModule>> getVehicleModulesByType(VehicleModule.Type moduleType, int vehicleId) {
        switch (moduleType) {
            case GUN: {
                return getGunsForVehicle(vehicleId, moduleType);
            }
            case ENGINE: {
                return getEnginesForVehicle(vehicleId, moduleType);
            }
            case TURRET: {
                return getTurretsForVehicle(vehicleId, moduleType);
            }
            case SUSPENSION: {
                return getSuspensionsForVehicle(vehicleId, moduleType);
            }
        }
        return Single.error(new IllegalArgumentException("Illegal module type " + moduleType.name()));
    }

    private Single<DetailVehicleDataModel> getVehicleDetailDataModel(int vehicleId) {
        return getVehicleDetailInfoFromDatabase(vehicleId)
                .flatMap(model -> {
                    if (model.getTankId() == null) return getVehicleDetailInfoFromServer(vehicleId);
                    return Single.just(model);
                });
    }

    private Single<DetailVehicleDataModel> getVehicleDetailInfoFromDatabase(int vehicleId) {
        return mVehiclesRepository.getDetailVehicleInfoFromDatabase(vehicleId);
    }

    private Single<DetailVehicleDataModel> getVehicleDetailInfoFromServer(int vehicleId) {
        return mVehiclesRepository.getDetailVehicleInfoFromServer(vehicleId)
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(response.getError());
                    }
                    return response;
                })
                .map(BaseResponse::getData)
                .map(map -> map.get(new ArrayList<>(map.keySet()).get(0)))
                .doOnNext(this::prepareModelAndSaveToRealm)
                .singleOrError();
    }

    private Single<List<? extends VehicleModule>> getTurretsForVehicle(int vehicleId, VehicleModule.Type type) {
        return getTurretsFromDatabase(vehicleId)
                .flatMap(turrets -> {
                    if (turrets.isEmpty()) return getTurretsFromServer(vehicleId, type);
                    return Single.just(turrets);
                });
    }

    private Single<List<? extends VehicleModule>> getGunsForVehicle(int vehicleId, VehicleModule.Type type) {
        return getGunsFromDatabase(vehicleId)
                .flatMap(guns -> {
                    if (guns.isEmpty()) return getGunsFromServer(vehicleId, type);
                    return Single.just(guns);
                });
    }

    private Single<List<? extends VehicleModule>> getEnginesForVehicle(int vehicleId, VehicleModule.Type type) {
        return getEnginesFromDatabase(vehicleId)
                .flatMap(engines -> {
                    if (engines.isEmpty()) return getEnginesFromServer(vehicleId, type);
                    return Single.just(engines);
                });
    }

    private Single<List<? extends VehicleModule>> getSuspensionsForVehicle(int vehicleId, VehicleModule.Type type) {
        return getSuspensionsFromDatabase(vehicleId)
                .flatMap(suspensions -> {
                    if (suspensions.isEmpty()) return getSuspensionsFromServer(vehicleId, type);
                    return Single.just(suspensions);
                });
    }

    private Single<List<EngineDataModel>> getEnginesFromDatabase(int vehicleId) {
        return mVehiclesRepository.getEnginesForVehicleFromDatabase(vehicleId);
    }

    private Single<List<SuspensionDataModel>> getSuspensionsFromDatabase(int vehicleId) {
        return mVehiclesRepository.getSuspensionsForVehicleFromDatabase(vehicleId);
    }

    private Single<List<GunDataModel>> getGunsFromDatabase(int vehicleId) {
        return mVehiclesRepository.getGunsForVehicleFromDatabase(vehicleId);
    }

    private Single<List<TurretDataModel>> getTurretsFromDatabase(int vehicleId) {
        return mVehiclesRepository.getTurretsForVehicleFromDatabase(vehicleId);
    }

    private Single<List<TurretDataModel>> getTurretsFromServer(int vehicleId, VehicleModule.Type type) {
        return getVehicleDetailDataModel(vehicleId)
                .map(DetailVehicleDataModel::getTurretsArray)
                .compose(serverResponseTransform(type))
                .flatMapObservable(Observable::fromIterable)
                .cast(TurretDataModel.class)
                .doOnNext(turret -> mVehiclesRepository.saveModel(turret).subscribe())
                .toList();
    }

    private Single<List<EngineDataModel>> getEnginesFromServer(int vehicleId, VehicleModule.Type type) {
        return getVehicleDetailDataModel(vehicleId)
                .map(DetailVehicleDataModel::getEnginesArray)
                .compose(serverResponseTransform(type))
                .flatMapObservable(Observable::fromIterable)
                .cast(EngineDataModel.class)
                .doOnNext(engine -> mVehiclesRepository.saveModel(engine).subscribe())
                .toList();
    }

    private Single<List<SuspensionDataModel>> getSuspensionsFromServer(int vehicleId, VehicleModule.Type type) {
        return getVehicleDetailDataModel(vehicleId)
                .map(DetailVehicleDataModel::getSuspensionsArray)
                .compose(serverResponseTransform(type))
                .flatMapObservable(Observable::fromIterable)
                .cast(SuspensionDataModel.class)
                .doOnNext(model -> mVehiclesRepository.saveModel(model).subscribe())
                .toList();
    }

    private Single<List<GunDataModel>> getGunsFromServer(int vehicleId, VehicleModule.Type type) {
        return getVehicleDetailDataModel(vehicleId)
                .map(DetailVehicleDataModel::getGunsArray)
                .compose(serverResponseTransform(type))
                .flatMapObservable(Observable::fromIterable)
                .cast(GunDataModel.class)
                .doOnNext(gun -> mVehiclesRepository.saveModel(gun).subscribe())
                .toList();
    }

    private SingleTransformer<Integer[], List<? extends VehicleModule>> serverResponseTransform(VehicleModule.Type type) {
        return upstream -> upstream
                .flatMap(ids -> mVehiclesRepository.getModulesVehicleFromServer(ids, type.getTypeForRequest()))
                .map(Response::body)
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(response.getError());
                    }
                    return response;
                })
                .map(BaseResponse::getData)
                .map(data -> getModulesFromResponseByType(type, data));
    }

    private DetailVehicleUIModel mapDataModel(DetailVehicleDataModel dataModel) {
        String nation = mVehiclesRepository.getVehicleNationNameByCode(dataModel.getNation())
                .blockingGet();

        String type = mVehiclesRepository.getVehicleTypeNameByCode(dataModel.getType())
                .blockingGet();

        return new DetailVehicleUIModel(
                dataModel.getTankId(),
                dataModel.getDescription(),
                nation,
                dataModel.getImages(),
                dataModel.getCost(),
                mapProfileModel(dataModel.getDefaultProfile()),
                dataModel.getTier(),
                type,
                dataModel.getName(),
                new ArrayList<>(dataModel.getEngines()),
                new ArrayList<>(dataModel.getSuspensions()),
                new ArrayList<>(dataModel.getGuns()),
                new ArrayList<>(dataModel.getTurrets()),
                new ArrayList<>(dataModel.getPrices()),
                new ArrayList<>(dataModel.getNextTanksList()),
                mapModulesList(dataModel.getModulesList()),
                dataModel.getPremium() == null ? false : dataModel.getPremium()
        );
    }

    private List<ModuleUIModel> mapModulesList(List<ModuleDataModel> dataModels) {
        if (dataModels == null || dataModels.isEmpty()) return Collections.emptyList();

        List<ModuleUIModel> modules = new ArrayList<>(dataModels.size());
        for (ModuleDataModel dataModel : dataModels) {
            List<Integer> nextTanks = dataModel.getNextTanks() == null
                    ? Collections.emptyList()
                    : new ArrayList<>(dataModel.getNextTanks());
            List<Integer> nextModules = dataModel.getNextModules() == null
                    ? Collections.emptyList()
                    : new ArrayList<>(dataModel.getNextModules());
            modules.add(new ModuleUIModel(
                    dataModel.getModuleId(),
                    dataModel.getName(),
                    dataModel.getDefault(),
                    dataModel.getPriceXp(),
                    dataModel.getPriceCredit(),
                    dataModel.getType(),
                    nextTanks,
                    nextModules
            ));
        }
        return modules;
    }

    private GunUIModel mapGunModel(GunDataModel dataModel, int id) {
        if (dataModel == null) return null;
        return new GunUIModel(
                dataModel.getMoveDownArc(),
                dataModel.getCaliber(),
                dataModel.getName(),
                dataModel.getWeight(),
                dataModel.getMoveUpArc(),
                dataModel.getFireRate(),
                dataModel.getClipReloadTime(),
                dataModel.getDispersion(),
                dataModel.getClipCapacity(),
                dataModel.getTraverseSpeed(),
                dataModel.getReloadTime(),
                dataModel.getTier(),
                dataModel.getAimTime(),
                id
        );
    }

    private ProfileUIModel mapProfileModel(ProfileDataModel dataModel) {
        if (dataModel == null) return null;

        return new ProfileUIModel(
                dataModel.getWeight(),
                dataModel.getProfileId(),
                dataModel.getFirepower(),
                dataModel.getShotEfficiency(),
                dataModel.getGunId(),
                dataModel.getSignalRange(),
                dataModel.getSpeedForward(),
                dataModel.getBattleLevelRangeMin(),
                dataModel.getSpeedBackward(),
                dataModel.getEngine(),
                dataModel.getMaxAmmo(),
                dataModel.getBattleLevelRangeMax(),
                dataModel.getEngineId(),
                dataModel.getHp(),
                dataModel.getDefault(),
                dataModel.getProtection(),
                dataModel.getSuspension(),
                dataModel.getSuspensionId(),
                dataModel.getMaxWeight(),
                mapGunModel(dataModel.getGun(), dataModel.getGunId()),
                dataModel.getTurretId(),
                dataModel.getTurret(),
                dataModel.getManeuverability(),
                dataModel.getHullWeight(),
                dataModel.getHullHp(),
                new ArrayList<>(dataModel.getShells()),
                new ArrayList<>(dataModel.getArmorsList())
        );
    }

    private ShortVehicleInfoUIModel mapToUIModel(ShortVehicleInfoDataModel dataModel) {
        String cost = dataModel.getCost() == null
                ? String.valueOf(0)
                : (dataModel.getCost().getPriceCredit() != null
                ? String.valueOf(dataModel.getCost().getPriceCredit())
                : String.valueOf(dataModel.getCost().getPriceGold()));
        boolean isPremium = dataModel.getCost() != null && dataModel.getCost().getPriceGold() != null;
        return new ShortVehicleInfoUIModel(
                dataModel.getTankId(),
                dataModel.getName(),
                dataModel.getDescription(),
                String.valueOf(dataModel.getTier()),
                dataModel.getImages().getPreview(),
                cost,
                isPremium
        );
    }

    private List<? extends VehicleModule> getModulesFromResponseByType(VehicleModule.Type type,
                                                                       ModulesDataModel dataModel) {
        switch (type) {
            case TURRET: {
                return dataModel.getTurrets();
            }
            case SUSPENSION: {
                return dataModel.getSuspensions();
            }
            case ENGINE: {
                return dataModel.getEngines();
            }
            case GUN: {
                return dataModel.getGuns();
            }
        }
        throw new IllegalArgumentException("Incorrect type " + type);
    }

    private void prepareModelAndSaveToRealm(DetailVehicleDataModel model) {
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

        mVehiclesRepository.saveModel(model).subscribe();
    }

}
