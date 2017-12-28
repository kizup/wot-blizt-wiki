package ru.kizup.wotblitzhelper.presentation.presenter.common_info;

import ru.kizup.wotblitzhelper.models.common_info.CommonInfoUIModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoCache {

    private CommonInfoUIModel mCommonInfo;

    public void updateCommonInfo(CommonInfoUIModel info) {
        mCommonInfo = info;
    }

    public boolean isCacheExists() {
        return mCommonInfo != null;
    }

    public CommonInfoUIModel getCommonInfo() {
        return mCommonInfo;
    }
}
