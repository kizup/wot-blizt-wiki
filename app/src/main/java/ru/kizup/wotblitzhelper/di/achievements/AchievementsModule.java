package ru.kizup.wotblitzhelper.di.achievements;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.achievements.AchievementsInteractor;
import ru.kizup.wotblitzhelper.business.achievements.IAchievementsInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.achievements.AchievementsRepository;
import ru.kizup.wotblitzhelper.data.repositories.achievements.IAchievementsRepository;
import ru.kizup.wotblitzhelper.presentation.presenter.achievements.AchievementsPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.achievements.IAchievementsPresenter;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class AchievementsModule {

    @Provides
    @AchievementsScope
    IAchievementsPresenter provideAchievementsPresenter(IAchievementsInteractor interactor,
                                                        RxSchedulersAbs rxSchedulersAbs,
                                                        ResponseValidator validator) {
        return new AchievementsPresenter(interactor, rxSchedulersAbs, validator);
    }

    @Provides
    @AchievementsScope
    IAchievementsRepository provideAchievementsRepository(IApiService service, IDatabaseHelper helper) {
        return new AchievementsRepository(service, helper);
    }

    @Provides
    @AchievementsScope
    IAchievementsInteractor provideAchievementsInteractor(IAchievementsRepository repository,
                                                          ResponseValidator validator) {
        return new AchievementsInteractor(repository, validator);
    }

}
