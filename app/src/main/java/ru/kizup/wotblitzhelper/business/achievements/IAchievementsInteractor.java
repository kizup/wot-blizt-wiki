package ru.kizup.wotblitzhelper.business.achievements;

import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.presentation.achievements.models.Achievement;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsInteractor {

    Single<List<Achievement>> getAchievementsShortInfo();

}
