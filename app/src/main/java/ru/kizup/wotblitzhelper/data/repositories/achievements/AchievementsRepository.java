package ru.kizup.wotblitzhelper.data.repositories.achievements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.achievements.IAchievementsService;
import ru.kizup.wotblitzhelper.data.network.achievements.response.AchievementsModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsRepository implements IAchievementsRepository {

    private IAchievementsService mAchievementsService;
    private ResponseValidator mResponseValidator;

    public AchievementsRepository(IAchievementsService achievementsService, ResponseValidator responseValidator) {
        mAchievementsService = achievementsService;
        mResponseValidator = responseValidator;
    }

    @Override
    public Single<HashMap<String, AchievementsModel>> getAchievementsShortInfo() {
        return mAchievementsService.getAchievements("achievement_id,name,image,order")
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(mResponseValidator.getErrorDescription(response.getError()));
                    }
                    return response;
                })
                .map(BaseResponse::getData)
                .singleOrError();
    }
}
