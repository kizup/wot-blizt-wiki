package ru.kizup.wotblitzhelper.data.repositories.common_info;

import io.reactivex.Single;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoRepository
        implements ICommonInfoRepository {

    private IApiService mApiService;

    public CommonInfoRepository(IApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<CommonInfoDataModel> getCommonInfo() {
        return mApiService.getCommonInfo()
                .map(Response::body)
                .flatMap(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.just(response);
                })
                .map(BaseResponse::getData);
    }
}
