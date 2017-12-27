package ru.kizup.wotblitzhelper.data.repositories.common_info;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.common_info.response.CommonInfoModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICommonInfoRepository {

    Single<BaseResponse<CommonInfoModel>> getCommonInfo();

}
