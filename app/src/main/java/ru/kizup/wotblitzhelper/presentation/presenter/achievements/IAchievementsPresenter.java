package ru.kizup.wotblitzhelper.presentation.presenter.achievements;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.models.achievements.AchievementUIModel;
import ru.kizup.wotblitzhelper.presentation.view.achievements.IAchievementsView;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsPresenter extends BasePresenter<IAchievementsView> {

    void loadShortAchievementsInfo();

    void onAchievementClick(AchievementUIModel model);

}
