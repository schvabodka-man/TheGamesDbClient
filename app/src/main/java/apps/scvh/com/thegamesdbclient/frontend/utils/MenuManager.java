package apps.scvh.com.thegamesdbclient.frontend.utils;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;

/**
 * Click handlers and menu generator
 */
public class MenuManager {

    private ApiKeyDialogManager apiManager;
    private Activity activity;

    public MenuManager(ApiKeyDialogManager apiManager, Activity activity) {
        this.apiManager = apiManager;
        this.activity = activity;
    }

    public MenuItem initMenuForGameDev(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return menu.findItem(R.id.menu_item_share);
    }

    public void initMenuForSearch(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        searchInit(menu);
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

    private void searchInit(Menu menu) {
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(false);
        SearchManager searchManager =
                (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
    }

}

