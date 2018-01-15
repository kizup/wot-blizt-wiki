package ru.kizup.wotblitzhelper.data.repositories.achievements;

import java.util.HashMap;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.Repository;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;
import ru.kizup.wotblitzhelper.models.common_info.AchievementDao;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsRepository extends Repository
        implements IAchievementsRepository {

    public AchievementsRepository(IApiService achievementsService, IDatabaseHelper helper) {
        super(achievementsService, helper);
    }

    @Override
    public Single<HashMap<String, AchievementsModel>> getAchievementsShortInfo() {
        return getDatabaseHelper().getAchievements();
    }
}
