package ru.kizup.wotblitzhelper.business.achievements;

import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.achievements.AchievementUIModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsInteractor {

    Single<List<AchievementUIModel>> getAchievementsShortInfo();

}
