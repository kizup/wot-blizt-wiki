package ru.kizup.wotblitzhelper.di.common_info;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.common_info.CommonInfoInteractor;
import ru.kizup.wotblitzhelper.business.common_info.ICommonInfoInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.common_info.CommonInfoRepository;
import ru.kizup.wotblitzhelper.data.repositories.common_info.ICommonInfoRepository;
import ru.kizup.wotblitzhelper.presentation.presenter.common_info.CommonInfoCache;
import ru.kizup.wotblitzhelper.presentation.presenter.common_info.CommonInfoPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.common_info.ICommonInfoPresenter;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class CommonInfoModule {

    @Provides
    @CommonInfoScope
    ICommonInfoPresenter provideCommonInfoPresenter(ICommonInfoInteractor interactor,
                                                    RxSchedulersAbs rxSchedulersAbs,
                                                    CommonInfoCache cache,
                                                    ResponseValidator validator) {
        return new CommonInfoPresenter(interactor, rxSchedulersAbs, cache, validator);
    }

    @Provides
    @CommonInfoScope
    ICommonInfoRepository providesCommonInfoRepository(IApiService service, IDatabaseHelper helper) {
        return new CommonInfoRepository(service, helper);
    }

    @Provides
    @CommonInfoScope
    ICommonInfoInteractor provideCommonInfoInteractor(ICommonInfoRepository repository,
                                                      ResponseValidator validator) {
        return new CommonInfoInteractor(repository, validator);
    }

}
