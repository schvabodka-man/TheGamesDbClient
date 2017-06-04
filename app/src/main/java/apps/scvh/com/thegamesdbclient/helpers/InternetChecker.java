package apps.scvh.com.thegamesdbclient.helpers;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import apps.scvh.com.thegamesdbclient.frontend.utils.Toaster;

public class InternetChecker {

    public static boolean isThereInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isInternet = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (!isInternet) {
            Toaster.showNoInternet(context);
        }
        return isInternet;
    }
}
