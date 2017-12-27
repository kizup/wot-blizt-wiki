package ru.kizup.wotblitzhelper.di.achievements;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.kizup.wotblitzhelper.business.achievements.AchievementsInteractor;
import ru.kizup.wotblitzhelper.business.achievements.IAchievementsInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.network.achievements.IAchievementsService;
import ru.kizup.wotblitzhelper.data.repositories.achievements.AchievementsRepository;
import ru.kizup.wotblitzhelper.data.repositories.achievements.IAchievementsRepository;
import ru.kizup.wotblitzhelper.presentation.achievements.presenter.AchievementsPresenter;
import ru.kizup.wotblitzhelper.presentation.achievements.presenter.IAchievementsPresenter;
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
    IAchievementsPresenter provideAchievementsPresenter(IAchievementsInteractor interactor, RxSchedulersAbs rxSchedulersAbs) {
        return new AchievementsPresenter(interactor, rxSchedulersAbs);
    }

    @Provides
    @AchievementsScope
    IAchievementsRepository provideAchievementsRepository(IAchievementsService service,
                                                          ResponseValidator validator) {
        return new AchievementsRepository(service, validator);
    }

    @Provides
    @AchievementsScope
    IAchievementsInteractor provideAchievementsInteractor(IAchievementsRepository repository,
                                                          ResponseValidator validator) {
        return new AchievementsInteractor(repository, validator);
    }

    @Provides
    @AchievementsScope
    IAchievementsService provideAchievementsService(Retrofit retrofit) {
        return retrofit.create(IAchievementsService.class);
    }

}
