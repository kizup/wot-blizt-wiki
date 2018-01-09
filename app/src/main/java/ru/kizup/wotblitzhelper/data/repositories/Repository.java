package ru.kizup.wotblitzhelper.data.repositories;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.models.common_info.AchievementInfoDataModel;
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
    public Single<List<AchievementInfoDataModel>> getAllAchievementSections() {
        return mApiService.getCommonInfo("vehicle_nations")
                .flatMapSingle(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData)
                .map(CommonInfoDataModel::getAchievements)
                .flatMap(map -> Observable.fromIterable(map.keySet())
                        .doOnNext(key -> map.get(key).setCode(key))
                        .map(map::get))
                .toList();
    }

    private Single<HashMap<String, String>> getAllVehicleNationsFromServer() {
        return mApiService.getCommonInfo("vehicle_nations")
                .flatMapSingle(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData)
                .map(CommonInfoDataModel::getVehicleNations)
                .doOnNext(this::saveVehicleNations)
                .singleOrError();
    }

    private Single<HashMap<String, String>> getAllVehicleTypesFromServer() {
        return mApiService.getCommonInfo("vehicle_types")
                .flatMapSingle(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData)
                .map(CommonInfoDataModel::getVehicleTypes)
                .doOnNext(this::saveVehicleTypes)
                .singleOrError();
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
