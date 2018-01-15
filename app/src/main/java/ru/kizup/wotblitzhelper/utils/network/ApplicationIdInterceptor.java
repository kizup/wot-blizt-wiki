package ru.kizup.wotblitzhelper.utils.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ApplicationIdInterceptor implements Interceptor {

    private static final String APP_ID = "ab9534f39d04fe66d549744c848692ab";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter("application_id", APP_ID)
                .build();
        return chain.proceed(request.newBuilder().url(url).build());
    }
}
