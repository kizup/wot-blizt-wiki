package ru.kizup.wotblitzhelper.business.common_info;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoUIModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICommonInfoInteractor {

    Single<CommonInfoUIModel> getCommonInfo();

}
