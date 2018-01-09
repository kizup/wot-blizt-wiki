package ru.kizup.wotblitzhelper.presentation.presenter.main;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.presentation.view.main.IMainView;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IMainPresenter extends BasePresenter<IMainView> {

    void clickOnCommonInfo();

    void clickOnAchievements();

    void clickOnCrewSkills();

    void clickOnVehicles();

}
