package ru.kizup.wotblitzhelper.business.achievements;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;
import ru.kizup.wotblitzhelper.data.repositories.achievements.IAchievementsRepository;
import ru.kizup.wotblitzhelper.models.achievements.AchievementUIModel;

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
    public Single<List<AchievementUIModel>> getAchievementsShortInfo() {
        return mAchievementsRepository.getAchievementsShortInfo()
                .flatMapObservable(map -> Observable.fromIterable(map.values()))
                .filter(this::modelFilter)
                .map(this::mapModel)
                .toSortedList((o1, o2) -> o1.getOrder() - o2.getOrder());
    }

    private boolean modelFilter(AchievementsModel model) {
        return model.getImage() != null
                && !model.getImage().isEmpty()
                && model.getName() != null
                && !model.getName().isEmpty();
    }

    private AchievementUIModel mapModel(AchievementsModel model) {
        return new AchievementUIModel(model.getName(),
                model.getImage(),
                model.getImageBig(),
                model.getOrder(),
                model.getAchievementId(),
                model.getDescription()
        );
    }

}
