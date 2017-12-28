package ru.kizup.wotblitzhelper.presentation.view.common_info;

import ru.kizup.wotblitzhelper.base.BaseView;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICommonInfoView extends BaseView {

    void showLoading();

    void hideLoading();

    void showFailureResponseError(String description);

    void showCommonError();

    void setUpdatedAt(String formattedDate);

    void setGameVersion(String version);

    void setVehicleTypesCount(int size);

    void showAchievementsScreen();

}
