package com.teja.testmvc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 01-02-2021.
 * Teja Reddy
 * FininfoCom Pvt Ltd
 */
public class Util {

    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {

            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable();
        }
        return false;

    }
}
