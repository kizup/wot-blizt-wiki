package ru.kizup.wotblitzhelper.business.achievements;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.network.achievements.response.AchievementsModel;
import ru.kizup.wotblitzhelper.data.repositories.achievements.IAchievementsRepository;
import ru.kizup.wotblitzhelper.presentation.achievements.models.Achievement;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsInteractor implements IAchievementsInteractor {

    private IAchievementsRepository mAchievementsRepository;
    private ResponseValidator mResponseValidator;

    public AchievementsInteractor(IAchievementsRepository achievementsRepository,
                                  ResponseValidator responseValidator) {
        mAchievementsRepository = achievementsRepository;
        mResponseValidator = responseValidator;
    }

    @Override
    public Single<List<Achievement>> getAchievementsShortInfo() {
        return mAchievementsRepository.getAchievementsShortInfo()
                .flatMapObservable(map -> Observable.fromIterable(map.values()))
                .map(this::mapModel)
                .toSortedList((o1, o2) -> o1.getOrder() - o2.getOrder());
    }

    private Achievement mapModel(AchievementsModel model) {
        return new Achievement(model.getName(),
                model.getImage(),
                model.getSection(),
                model.getOrder(),
                model.getAchievementId()
        );
    }

}
