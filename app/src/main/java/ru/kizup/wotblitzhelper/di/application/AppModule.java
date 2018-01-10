package ru.kizup.wotblitzhelper.di.application;

import android.content.Context;

import com.squareup.picasso.Picasso;

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
    private final Picasso mPicasso;

    public AppModule(@Nonnull Context context) {
        mContext = context;
        Picasso.Builder builder = new Picasso.Builder(mContext);
        mPicasso = builder.build();
        Picasso.setSingletonInstance(mPicasso);
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mContext;
    }

    @Provides
    @Singleton
    Picasso providePicasso() {
        return mPicasso;
    }

}
