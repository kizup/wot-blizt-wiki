package ru.kizup.wotblitzhelper.data.network.common_info;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.common_info.response.CommonInfoModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICommonInfoService {

    @GET("info/")
    Observable<BaseResponse<CommonInfoModel>> getCommonInfo();

    @GET("info/")
    Observable<BaseResponse<CommonInfoModel>> getCommonInfo(@Query("language") String lang);

}
