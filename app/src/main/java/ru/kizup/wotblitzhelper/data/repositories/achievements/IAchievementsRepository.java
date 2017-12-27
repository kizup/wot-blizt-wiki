package ru.kizup.wotblitzhelper.data.repositories.achievements;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.data.network.achievements.response.AchievementsModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsRepository {

    Single<HashMap<String, AchievementsModel>> getAchievementsShortInfo();

}
