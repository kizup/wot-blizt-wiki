package ru.kizup.wotblitzhelper.data.repositories.achievements;

import java.util.HashMap;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsRepository implements IAchievementsRepository {

    private IApiService mAchievementsService;

    public AchievementsRepository(IApiService achievementsService) {
        mAchievementsService = achievementsService;
    }

    @Override
    public Single<HashMap<String, AchievementsModel>> getAchievementsShortInfo() {
        return mAchievementsService.getAchievements("achievement_id,image,image_big,description,order,name")
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(response.getError());
                    }
                    return response;
                })
                .map(BaseResponse::getData)
                .singleOrError();
    }
}
