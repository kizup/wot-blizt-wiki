package ru.kizup.wotblitzhelper.presentation.common_info.presenter;

import ru.kizup.wotblitzhelper.presentation.common_info.models.CommonInfo;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoCache {

    private CommonInfo mCommonInfo;

    public void updateCommonInfo(CommonInfo info) {
        mCommonInfo = info;
    }

    public boolean isCacheExists() {
        return mCommonInfo != null;
    }

    public CommonInfo getCommonInfo() {
        return mCommonInfo;
    }
}
