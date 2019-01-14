package com.mickaeldebalme.android.newsletter.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkHelper {

    private static boolean networkSatus;

    public static void init(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        if (isConnected) {
            networkSatus = true;
            System.out.println("Internet detected");
        } else {
            networkSatus = false;
            System.out.println("No internet");
        }
    }

    public static boolean getNetworkStatus() {
        return networkSatus;
    }
}
