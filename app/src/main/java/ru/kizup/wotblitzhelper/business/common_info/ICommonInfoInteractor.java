package ru.kizup.wotblitzhelper.business.common_info;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.presentation.common_info.models.CommonInfo;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICommonInfoInteractor {

    Single<CommonInfo> getCommonInfo();

}
