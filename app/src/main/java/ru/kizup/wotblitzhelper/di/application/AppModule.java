package ru.kizup.wotblitzhelper.di.application;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Cache;
import com.squareup.picasso.OkHttpDownloader;
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
    private final OkHttp3Downloader mOkHttp3Downloader;

    public AppModule(@Nonnull Context context) {
        mContext = context;
        mOkHttp3Downloader = new OkHttp3Downloader(mContext.getCacheDir(), Integer.MAX_VALUE);
        Picasso.Builder builder = new Picasso.Builder(mContext)
                .downloader(mOkHttp3Downloader);
        mPicasso = builder.build();
        Picasso.setSingletonInstance(mPicasso);
    }

    @Provides
    @Singleton
    OkHttp3Downloader provideOkHttp3Downloader() {
        return mOkHttp3Downloader;
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
