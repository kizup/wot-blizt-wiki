package ru.kizup.wotblitzhelper.presentation.presenter.common_info;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.presentation.view.common_info.ICommonInfoView;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICommonInfoPresenter extends BasePresenter<ICommonInfoView> {

    void loadCommonInfo();

    void clickAchievementsButton();

}
