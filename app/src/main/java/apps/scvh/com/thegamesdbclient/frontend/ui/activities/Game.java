package apps.scvh.com.thegamesdbclient.frontend.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.dagger.comp.Injector;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.LoadingDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.injectors.GameViewsInjector;
import apps.scvh.com.thegamesdbclient.frontend.utils.MenuManager;
import apps.scvh.com.thegamesdbclient.frontend.utils.RecyclerViewWorker;
import apps.scvh.com.thegamesdbclient.frontend.utils.ShareManager;
import apps.scvh.com.thegamesdbclient.frontend.utils.ToolbarStylizer;
import apps.scvh.com.thegamesdbclient.helpers.InternetChecker;
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
    @Named("DialogManager")
    LoadingDialogManager dialogManager;

    @Inject
    @Named("MenuManager")
    MenuManager menuManager;

    @Inject
    @Named("RecyclerProvider")
    RecyclerViewWorker recyclerViewWorker;

    @Inject
    @Named("ShareManager")
    ShareManager shareManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InternetChecker.isThereInternet(this)) {
            setContentView(R.layout.activity_game);
            Injector.inject(this);
            ToolbarStylizer.stylizeToolbar(getSupportActionBar());
            dialogManager.showDialogWithClickListener(new ProgressDialog(this));
            viewsInjector.setViewWorker(recyclerViewWorker);
            viewsInjector.populateUI(receiveGameIDFromIntent(), dialogManager.getDialog());
        } else {
            finish();
        }
    }

    private Observable<GameData> receiveGameIDFromIntent() {
        return retriever.getGame(getIntent().getIntExtra(getString(R.string.bundle_id), 1))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem searchButton = menuManager.initMenuForGameDev(menu);
        initShareProvider((ShareActionProvider) MenuItemCompat.getActionProvider(searchButton));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuManager.gameDevMenuClickManager(item);
        return true;
    }

    private void initShareProvider(ShareActionProvider provider) {
        receiveGameIDFromIntent().subscribe(data1 -> shareManager.setShareIntent(data1.getUrl(),
                provider));
    }

}
