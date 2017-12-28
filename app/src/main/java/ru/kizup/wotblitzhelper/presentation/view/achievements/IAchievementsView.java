package ru.kizup.wotblitzhelper.presentation.view.achievements;

import java.util.List;

import ru.kizup.wotblitzhelper.base.BaseView;
import ru.kizup.wotblitzhelper.models.achievements.AchievementUIModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsView extends BaseView {

    void showLoading();

    void hideLoading();

    void showLoadedAchievements(List<AchievementUIModel> achievementUIModels);

    void showDetailAchievementDialog(AchievementUIModel model);

}
