package ru.kizup.wotblitzhelper.data.repositories.achievements;

import java.util.HashMap;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.data.repositories.IRepository;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsRepository extends IRepository {

    Single<HashMap<String, AchievementsModel>> getAchievementsShortInfo();

}
