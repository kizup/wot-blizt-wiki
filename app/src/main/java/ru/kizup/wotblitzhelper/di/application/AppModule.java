package ru.kizup.wotblitzhelper.di.application;

import android.content.Context;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class AppModule {

    private final Context mContext;


    public AppModule(@Nonnull Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mContext;
    }

}
