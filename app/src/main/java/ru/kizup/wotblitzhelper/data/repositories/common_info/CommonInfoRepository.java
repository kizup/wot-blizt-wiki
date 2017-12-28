package ru.kizup.wotblitzhelper.data.repositories.common_info;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.business.common_info.CommonInfoLoadException;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoRepository implements ICommonInfoRepository {

    private IApiService mApiService;

    public CommonInfoRepository(IApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<CommonInfoDataModel> getCommonInfo() {
        return mApiService.getCommonInfo()
                .map(model -> {
                    if (model == null) {
                        throw new CommonInfoLoadException("CommonInfoUIModel is null");
                    }
                    return model;
                })
                .flatMapSingle(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData)
                .singleOrError();
    }
}
