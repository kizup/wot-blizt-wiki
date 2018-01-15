package ru.kizup.wotblitzhelper.di.main;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.main.IMainInteractor;
import ru.kizup.wotblitzhelper.business.main.MainInteractor;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.main.IMainRepository;
import ru.kizup.wotblitzhelper.data.repositories.main.MainRepository;
import ru.kizup.wotblitzhelper.presentation.presenter.main.IMainPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.main.MainPresenter;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class MainModule {

    @Provides
    @MainScope
    IMainPresenter provideMainPresenter(IMainInteractor interactor,
                                        RxSchedulersAbs rxSchedulersAbs,
                                        Context context) {
        return new MainPresenter(interactor, rxSchedulersAbs, context);
    }

    @Provides
    @MainScope
    IMainInteractor provideMainInteractor(IMainRepository repository,
                                          Context context) {
        return new MainInteractor(repository, context);
    }

    @Provides
    @MainScope
    IMainRepository provideMainRepository(IApiService service,
                                          IDatabaseHelper helper) {
        return new MainRepository(service, helper);
    }

}
