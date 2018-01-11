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
        return getDatabaseHelper().getAchievements()
                .flatMap(map -> {
                    if (map.isEmpty()) return getAchievementsFromServer();
                    return Single.just(map);
                });
    }

    private Single<HashMap<String, AchievementsModel>> getAchievementsFromServer() {
        return getApiService().getAchievements("achievement_id,image,image_big,description,order,name")
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(response.getError());
                    }
                    saveAchievements(response.getData());
                    return response;
                })
                .map(BaseResponse::getData);
    }

    private void saveAchievements(HashMap<String, AchievementsModel> map) {
        for (String key : map.keySet()) {
            AchievementsModel model = map.get(key);
            AchievementDao dao = new AchievementDao(key, model);
            getDatabaseHelper().saveModel(dao).subscribe();
        }
    }
}
