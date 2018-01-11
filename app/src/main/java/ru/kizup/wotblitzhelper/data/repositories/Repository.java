package ru.kizup.wotblitzhelper.data.repositories;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.models.common_info.AchievementSectionDataModel;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.models.common_info.VehicleNationDao;
import ru.kizup.wotblitzhelper.models.common_info.VehicleTypeDao;

/**
 * Created by: dpuzikov on 29.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class Repository implements IRepository {

    private IApiService mApiService;
    private IDatabaseHelper mDatabaseHelper;

    public Repository(IApiService apiService, IDatabaseHelper helper) {
        mApiService = apiService;
        mDatabaseHelper = helper;
    }

    protected IApiService getApiService() {
        return mApiService;
    }

    protected IDatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    @Override
    public Single<HashMap<String, String>> getAllVehicleTypes() {
        return mDatabaseHelper.getVehicleTypes()
                .flatMap(map -> {
                    if (map.isEmpty()) return getAllVehicleTypesFromServer();
                    return Single.just(map);
                });
    }

    @Override
    public Single<HashMap<String, String>> getAllVehicleNations() {
        return mDatabaseHelper.getVehicleNations()
                .flatMap(map -> {
                    if (map.isEmpty()) return getAllVehicleNationsFromServer();
                    return Single.just(map);
                });
    }

    @Override
    public Single<List<AchievementSectionDataModel>> getAllAchievementSections() {
        return mApiService.getCommonInfo("vehicle_nations")
                .compose(responseTransformer())
                .map(CommonInfoDataModel::getAchievementSections)
                .flatMapObservable(map -> Observable.fromIterable(map.keySet())
                        .doOnNext(s -> map.get(s).setCode(s))
                        .map(map::get))
                .toList();
    }

    private Single<HashMap<String, String>> getAllVehicleNationsFromServer() {
        return mApiService.getCommonInfo("vehicle_nations")
                .compose(responseTransformer())
                .map(CommonInfoDataModel::getVehicleNations)
                .doOnSuccess(this::saveVehicleNations);
    }

    private <D> SingleTransformer<Response<BaseResponse<D>>, D> responseTransformer() {
        return upstream -> upstream.map(Response::body)
                .flatMap(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData);
    }

    private Single<HashMap<String, String>> getAllVehicleTypesFromServer() {
        return mApiService.getCommonInfo("vehicle_types")
                .compose(responseTransformer())
                .map(CommonInfoDataModel::getVehicleTypes)
                .doOnSuccess(this::saveVehicleTypes);
    }

    private void saveVehicleTypes(HashMap<String, String> types) {
        for (String key : types.keySet()) {
            VehicleTypeDao dao = new VehicleTypeDao(key, types.get(key));
            mDatabaseHelper.saveModel(dao).subscribe();
        }
    }

    private void saveVehicleNations(HashMap<String, String> nations) {
        for (String key : nations.keySet()) {
            VehicleNationDao dao = new VehicleNationDao(key, nations.get(key));
            mDatabaseHelper.saveModel(dao).subscribe();
        }
    }

}
