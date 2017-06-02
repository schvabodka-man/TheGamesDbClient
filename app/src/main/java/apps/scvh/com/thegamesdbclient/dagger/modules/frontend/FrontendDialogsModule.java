package apps.scvh.com.thegamesdbclient.dagger.modules.frontend;


import android.app.Activity;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyUpdater;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.LoadingDialogManager;
import dagger.Module;
import dagger.Provides;

@Module
public class FrontendDialogsModule {

    private Activity activity;

    public FrontendDialogsModule(Activity activity) {
        this.activity = activity;
    }

    public FrontendDialogsModule() {
    }

    @Provides
    @Named("DialogManager")
    LoadingDialogManager dialogManager() {
        return new LoadingDialogManager(activity.getBaseContext());
    }

    @Provides
    @Named("ApiDialogManager")
    ApiKeyDialogManager apiKeyDialogManager(@Named("KeyUpdater") ApiKeyUpdater updater,
                                            @Named
            ("ApiKey") ApiKeyManager manager, @Named("DialogManager") LoadingDialogManager
                                                           dialogManager) {
        return new ApiKeyDialogManager(activity, updater, manager, dialogManager);
    }
}
