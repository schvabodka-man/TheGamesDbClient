package apps.scvh.com.thegamesdbclient.frontend.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.models.GameDeveloper;
import apps.scvh.com.thegamesdbclient.dagger.comp.Injector;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.LoadingDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.injectors.DeveloperInjector;
import apps.scvh.com.thegamesdbclient.frontend.utils.ShareManager;
import apps.scvh.com.thegamesdbclient.frontend.utils.ToolbarStylizer;
import io.reactivex.Observable;

public class Developer extends AppCompatActivity {

    @Inject
    @Named("DeveloperInjector")
    DeveloperInjector uiInjector;

    @Inject
    @Named("GameRetriever")
    GameRetriever retriever;

    @Inject
    @Named("DialogManager")
    LoadingDialogManager dialogManager;

    @Inject
    @Named("ShareManager")
    ShareManager shareManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        Injector.inject(this);
        ToolbarStylizer.stylizeToolbar(getSupportActionBar());
        dialogManager.showDialog(new ProgressDialog(this));
        uiInjector.populateUI(getDataFromIntent(), dialogManager.getDialog());
    }

    private Observable<GameDeveloper> getDataFromIntent() {
        return retriever.getDeveloper(getIntent().getIntExtra(getString(R.string.game_dev_key), 1));
    }
}
