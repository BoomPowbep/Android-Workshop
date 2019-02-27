package com.mickaeldebalme.android.newsletter.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkHelper {

    private static final String LOGTAG = "NetworkHelper";

    /**
     * Status de la connectivité
     */
    private static boolean networkSatus;

    /**
     * Initialisation
     * @param context contexte
     */
    public static void init(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        if (isConnected) {
            networkSatus = true;
            Log.d(LOGTAG, "Internet detected");
        } else {
            networkSatus = false;
            Log.d(LOGTAG,"No internet");
        }
    }

    /**
     * Récupère le statut de la connectivité
     * @return statut de la connectivité
     */
    public static boolean getNetworkStatus() {
        return networkSatus;
    }
}
