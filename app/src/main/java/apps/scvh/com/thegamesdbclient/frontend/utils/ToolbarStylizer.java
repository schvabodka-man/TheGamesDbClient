package apps.scvh.com.thegamesdbclient.frontend.utils;


import android.support.v7.app.ActionBar;

/**
 * Class i'm using for stylizing toolbar
 */
public class ToolbarStylizer {

    public static void stylizeToolbar(ActionBar bar) {
        bar.setShowHideAnimationEnabled(false);
        bar.setDisplayShowTitleEnabled(false);
        bar.setElevation(0);
        bar.setDisplayHomeAsUpEnabled(true);
    }

    public static void stylizeSearchToolbar(ActionBar bar) {
        bar.setDisplayShowTitleEnabled(false);
    }
}
