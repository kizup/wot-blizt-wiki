package ru.kizup.wotblitzhelper.di.common_info;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.kizup.wotblitzhelper.business.common_info.CommonInfoInteractor;
import ru.kizup.wotblitzhelper.business.common_info.ICommonInfoInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.network.common_info.ICommonInfoService;
import ru.kizup.wotblitzhelper.data.repositories.common_info.CommonInfoRepository;
import ru.kizup.wotblitzhelper.data.repositories.common_info.ICommonInfoRepository;
import ru.kizup.wotblitzhelper.presentation.common_info.presenter.CommonInfoCache;
import ru.kizup.wotblitzhelper.presentation.common_info.presenter.CommonInfoPresenter;
import ru.kizup.wotblitzhelper.presentation.common_info.presenter.ICommonInfoPresenter;
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
                                                    CommonInfoCache cache) {
        return new CommonInfoPresenter(interactor, rxSchedulersAbs, cache);
    }

    @Provides
    @CommonInfoScope
    CommonInfoCache provideCommonInfoCache() {
        return new CommonInfoCache();
    }

    @Provides
    @CommonInfoScope
    ICommonInfoRepository providesCommonInfoRepository(ICommonInfoService service) {
        return new CommonInfoRepository(service);
//        return new CommonInfoRepositoryTest();
    }

    @Provides
    @CommonInfoScope
    ICommonInfoInteractor provideCommonInfoInteractor(ICommonInfoRepository repository, ResponseValidator validator) {
        return new CommonInfoInteractor(repository, validator);
    }

    @Provides
    @CommonInfoScope
    ICommonInfoService provideApiService(Retrofit retrofit) {
        return retrofit.create(ICommonInfoService.class);
    }

}
