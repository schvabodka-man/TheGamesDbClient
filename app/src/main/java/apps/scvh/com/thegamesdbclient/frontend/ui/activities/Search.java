package apps.scvh.com.thegamesdbclient.frontend.ui.activities;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyChecker;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.dagger.comp.Injector;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.LoadingDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.list.SearchGamesAdapter;
import apps.scvh.com.thegamesdbclient.frontend.utils.RecyclerViewWorker;
import apps.scvh.com.thegamesdbclient.frontend.utils.Toaster;
import apps.scvh.com.thegamesdbclient.frontend.utils.ToolbarStylizer;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Search extends AppCompatActivity {

    @Inject
    @Named("GameRetriever")
    GameRetriever retriever;

    @Inject
    @Named("DialogManager")
    LoadingDialogManager loadingManager;

    @Inject
    @Named("ApiDialogManager")
    ApiKeyDialogManager apiManager;

    @Inject
    @Named("RecyclerProvider")
    RecyclerViewWorker recyclerViewWorker;

    @Inject
    @Named("KeyChecker")
    ApiKeyChecker apiKeyChecker;

    @BindView(R.id.search_list)
    LinearLayout recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Injector.inject(this);
        ButterKnife.bind(this);
        apiKeyChecker.loadDialogIfKeyIsNull();
        ToolbarStylizer.stylizeSearchToolbar(getSupportActionBar());
        recyclerViewWorker.initRecycler(recycler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        searchInit(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_menu) {
            apiManager.showDialog();
        }
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            loadingManager.showDialog(new ProgressDialog(this));
            handleSearch(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    private void searchInit(Menu menu) {
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(false);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    }

    private void handleSearch(String query) {
        retriever.searchGames(query).subscribe(games -> {
            if (games.size() != 0) {
                recyclerViewWorker.showRecycler(new SearchGamesAdapter(games, this),
                        LinearLayoutManager.VERTICAL);
                loadingManager.hideDialog();
            } else {
                loadingManager.hideDialog();
                Toaster.showNothingHasBeenFound(this);
            }
        });
    }


}
