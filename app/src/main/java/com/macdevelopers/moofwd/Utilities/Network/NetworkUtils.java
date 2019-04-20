package com.macdevelopers.moofwd.Utilities.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    /**
     * @param context
     * @return
     */
    public static boolean isNetworkConnectionOn(Context context) {

        try {

            boolean mobileFlag = false, wifiFlag = false;
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

            /*State mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

            State wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

            if (mobile == State.CONNECTED || mobile == State.CONNECTING) {
                mobileFlag = true;
            }
            if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
                wifiFlag = true;
            }*/

            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi

				/*if(NetworkInfo.State.CONNECTED.equals(true))*/
                    wifiFlag = true;

                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan

				/*if(NetworkInfo.State.CONNECTED.equals(true))*/
                    mobileFlag = true;

                }
            } else {
                // not connected to the internet
                return false;
            }

            if (wifiFlag == true || mobileFlag == true) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}

