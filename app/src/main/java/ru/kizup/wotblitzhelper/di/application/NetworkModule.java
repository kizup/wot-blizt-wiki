package ru.kizup.wotblitzhelper.di.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.kizup.wotblitzhelper.base.Constants;
import ru.kizup.wotblitzhelper.di.common_info.CommonInfoScope;
import ru.kizup.wotblitzhelper.utils.ApplicationIdInterceptor;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        ApplicationIdInterceptor applicationIdInterceptor = new ApplicationIdInterceptor();

        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(applicationIdInterceptor);

        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(Constants.API_URL);
        builder.client(client);
        return builder.build();
    }

}
