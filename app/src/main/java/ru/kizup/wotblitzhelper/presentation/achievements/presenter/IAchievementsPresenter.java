package ru.kizup.wotblitzhelper.presentation.achievements.presenter;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.presentation.achievements.view.IAchievementsView;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsPresenter extends BasePresenter<IAchievementsView> {

    void loadShortAchievementsInfo();

}
