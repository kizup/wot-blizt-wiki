package ru.kizup.wotblitzhelper.presentation.view.main;

import ru.kizup.wotblitzhelper.base.BaseView;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IMainView extends BaseView {

    void showCommonInfoScreen();

    void showAchievementsScreen();

    void showCrewSkillsScreen();

    void showVehiclesScreen();

    void showRequestUpdateDatabaseDialog();

    void showUpdateDialog();

    void hideUpdateDialog();
}
