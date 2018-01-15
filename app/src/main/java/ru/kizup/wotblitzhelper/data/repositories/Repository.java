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
        return mDatabaseHelper.getVehicleTypes();
    }

    @Override
    public Single<HashMap<String, String>> getAllVehicleNations() {
        return mDatabaseHelper.getVehicleNations();
    }

}
