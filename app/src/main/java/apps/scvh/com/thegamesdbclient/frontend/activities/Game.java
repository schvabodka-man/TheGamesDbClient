package apps.scvh.com.thegamesdbclient.frontend.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.dagger.comp.Injector;
import apps.scvh.com.thegamesdbclient.frontend.GameViewsInjector;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.LoadingDialogManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class Game extends AppCompatActivity {

    @Inject
    @Named("GameRetriever")
    GameRetriever retriever;

    @Inject
    @Named("ViewsInjector")
    GameViewsInjector viewsInjector;

    @Inject
    @Named("ApiDialogManager")
    ApiKeyDialogManager apiManager;

    @Inject
    @Named("DialogManager")
    LoadingDialogManager dialogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Injector.inject(this);
        initActionBar(getSupportActionBar());
        showProgressDialog(new ProgressDialog(this));
        viewsInjector.populateUI(receiveGameFromIntent(), dialogManager.getDialog());
    }

    private Observable<GameData> receiveGameFromIntent() {
        return retriever.getGame(getIntent().getIntExtra(getString(R.string.bundle_id), 1))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        MenuItem searchButton = menu.findItem(R.id.menu_item_share);
        initShareProvider((ShareActionProvider) MenuItemCompat.getActionProvider(searchButton));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_in_game) {
            apiManager.showDialog();
        }
        return true;
    }

    private void initActionBar(ActionBar bar) {
        bar.setShowHideAnimationEnabled(false);
        bar.setDisplayShowTitleEnabled(false);
        bar.setElevation(0);
        bar.setDisplayHomeAsUpEnabled(true);
    }

    private void initShareProvider(ShareActionProvider provider) {
        receiveGameFromIntent().subscribe(data1 -> setShareIntent(data1.getUrl(), provider));
    }

    private void setShareIntent(String url, ShareActionProvider actionProvider) {
        if (actionProvider != null) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType(getString(R.string.mime_type_for_sharing));
            sharingIntent.putExtra(Intent.EXTRA_TEXT, url);
            actionProvider.setShareIntent(Intent.createChooser(sharingIntent, getString(R.string
                    .share_url)));
        }
    }

    private void showProgressDialog(ProgressDialog dialog) {
        dialog.setOnCancelListener(dialog1 -> finish());
        dialogManager.showDialog(dialog);
    }
}
