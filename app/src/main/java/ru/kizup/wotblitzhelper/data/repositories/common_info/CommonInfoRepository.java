package ru.kizup.wotblitzhelper.data.repositories.common_info;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.common_info.ICommonInfoService;
import ru.kizup.wotblitzhelper.data.network.common_info.response.CommonInfoModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoRepository implements ICommonInfoRepository {

    private ICommonInfoService mApiService;

    public CommonInfoRepository(ICommonInfoService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<BaseResponse<CommonInfoModel>> getCommonInfo() {
        return mApiService.getCommonInfo()
                .singleOrError();
    }
}
