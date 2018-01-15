package ru.kizup.wotblitzhelper.utils.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.kizup.wotblitzhelper.data.network.ServerErrorException;

/**
 * Created by: dpuzikov on 12.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (response.code() >= 400 && response.code() <= 500) {
            throw new ServerErrorException();
        }

        return response;
    }
}
