package ru.kizup.wotblitzhelper.data.repositories.common_info;

import io.reactivex.Single;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.Repository;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoRepository extends Repository
        implements ICommonInfoRepository {

    public CommonInfoRepository(IApiService apiService, IDatabaseHelper helper) {
        super(apiService, helper);
    }

    @Override
    public Single<CommonInfoDataModel> getCommonInfo() {
        return getDatabaseHelper().getCommonWikiInfo();
    }
}
