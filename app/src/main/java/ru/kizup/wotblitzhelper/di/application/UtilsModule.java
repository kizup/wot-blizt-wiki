package ru.kizup.wotblitzhelper.di.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.VehiclesMapper;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.db.DatabaseHelper;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.di.common_info.CommonInfoScope;
import ru.kizup.wotblitzhelper.presentation.presenter.common_info.CommonInfoCache;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulers;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    IDatabaseHelper provideDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }

    @Provides
    @Singleton
    CommonInfoCache provideCommonInfoCache() {
        return new CommonInfoCache();
    }

    @Provides
    @Singleton
    RxSchedulersAbs provideRxSchedulers() {
        return new RxSchedulers();
    }

    @Provides
    @Singleton
    ResponseValidator provideResponseValidator(Context context) {
        return new ResponseValidator(context);
    }

    @Provides
    @Singleton
    VehiclesMapper provideVehiclesMapper() {
        return new VehiclesMapper();
    }

}
