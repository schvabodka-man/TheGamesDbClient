package apps.scvh.com.thegamesdbclient.frontend.utils;

import android.view.MenuItem;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;

public class MenuManager {

    private ApiKeyDialogManager apiManager;

    public MenuManager(ApiKeyDialogManager apiManager) {
        this.apiManager = apiManager;
    }

    public void initMenuForGameDev() {

    }

    public void initMenuForSearch() {

    }

    public void gameDevMenuClickManager(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.search_in_game) {
            apiManager.showDialog();
        }
    }

    public void searchMenuClickManager(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.search_menu) {
            apiManager.showDialog();
        }
    }
}

