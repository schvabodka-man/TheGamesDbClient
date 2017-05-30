package apps.scvh.com.thegamesdbclient.dagger.modules;


import android.app.Activity;

import javax.inject.Named;
import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyUpdater;
import apps.scvh.com.thegamesdbclient.frontend.GameViewsInjector;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.LoadingDialogManager;
import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class FrontendModule {

    private Activity activity;

    public FrontendModule(Activity activity) {
        this.activity = activity;
    }

    public FrontendModule() {
    }

    @Provides
    @Named("ViewsInjector")
    public GameViewsInjector viewsInjector() {
        return new GameViewsInjector((Game) activity);
    }

    @Provides
    @Named("DialogManager")
    LoadingDialogManager dialogManager() {
        return new LoadingDialogManager(activity.getBaseContext());
    }

    @Provides
    @Named("ApiDialogManager")
    ApiKeyDialogManager apiKeyDialogManager(@Named("KeyUpdater") ApiKeyUpdater updater, @Named
            ("ApiKey") ApiKeyManager manager, @Named("DialogManager") LoadingDialogManager
            dialogManager) {
        return new ApiKeyDialogManager(activity, updater, manager, dialogManager);
    }
}
