package apps.scvh.com.thegamesdbclient.frontend.utils;


import android.content.Context;
import android.widget.Toast;

import apps.scvh.com.thegamesdbclient.R;

/**
 * Class i'm using for showing toasts
 */
public class Toaster {

    public static void showEmptyKeyToast(Context context) {
        Toast.makeText(context, context.getString(R.string.empty_key), Toast.LENGTH_SHORT).show();
    }

    public static void showNothingHasBeenFound(Context context) {
        Toast.makeText(context, context.getString(R.string.nothing_has_been_found), Toast
                .LENGTH_SHORT).show();
    }

    public static void showNoInternet(Context context) {
        Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
    }

}
