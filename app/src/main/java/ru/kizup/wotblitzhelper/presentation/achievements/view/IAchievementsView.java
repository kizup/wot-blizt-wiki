package ru.kizup.wotblitzhelper.presentation.achievements.view;

import java.util.List;

import ru.kizup.wotblitzhelper.base.BaseView;
import ru.kizup.wotblitzhelper.presentation.achievements.models.Achievement;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsView extends BaseView {

    void showLoading();

    void hideLoading();

    void showLoadedAchievements(List<Achievement> achievements);

}
