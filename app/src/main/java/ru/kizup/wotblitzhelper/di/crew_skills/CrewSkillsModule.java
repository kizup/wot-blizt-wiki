package ru.kizup.wotblitzhelper.di.crew_skills;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.crew_skills.CrewSkillsInteractor;
import ru.kizup.wotblitzhelper.business.crew_skills.ICrewSkillsInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.crew_skills.CrewSkillsRepository;
import ru.kizup.wotblitzhelper.data.repositories.crew_skills.ICrewSkillsRepository;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.CrewSkillCache;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.CrewSkillsPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.ICrewSkillsPresenter;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class CrewSkillsModule {

    @Provides
    @CrewSkillsScope
    ICrewSkillsInteractor provideCrewSkillsInteractor(ICrewSkillsRepository repository) {
        return new CrewSkillsInteractor(repository);
    }

    @Provides
    @CrewSkillsScope
    ICrewSkillsRepository provideCrewSkillsRepository(IApiService service, IDatabaseHelper helper) {
        return new CrewSkillsRepository(service, helper);
    }

    @Provides
    @CrewSkillsScope
    CrewSkillCache provideCrewSkillCache() {
        return new CrewSkillCache();
    }

    @Provides
    @CrewSkillsScope
    ICrewSkillsPresenter provideCrewSkillsPresenter(ICrewSkillsInteractor interactor,
                                                    RxSchedulersAbs rxSchedulersAbs,
                                                    ResponseValidator validator,
                                                    CrewSkillCache crewSkillCache) {
        return new CrewSkillsPresenter(interactor, rxSchedulersAbs, validator, crewSkillCache);
    }

}
