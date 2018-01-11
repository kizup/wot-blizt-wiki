package ru.kizup.wotblitzhelper.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class NetworkUtil {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) return false;
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
