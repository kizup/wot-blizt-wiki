package ru.kizup.wotblitzhelper.di.main;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.presentation.presenter.main.IMainPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.main.MainPresenter;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class MainModule {

    @Provides
    @MainScope
    IMainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

}
