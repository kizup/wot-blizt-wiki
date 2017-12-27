package ru.kizup.wotblitzhelper.presentation.common_info.presenter;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.presentation.common_info.view.ICommonInfoView;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICommonInfoPresenter extends BasePresenter<ICommonInfoView> {

    void loadCommonInfo();

    void clickAchievementsButton();

}
