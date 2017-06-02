package apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers;

import android.content.Context;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyChecker;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyUpdater;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyWrite;
import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;
import dagger.Module;
import dagger.Provides;

@Module
public class KeysModule {

    private Context context;

    public KeysModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("ApiKey")
    ApiKeyManager apiKeyManager() {
        return new ApiKeyManager(context);
    }

    @Provides
    @Named("KeyUpdater")
    ApiKeyUpdater updater(@Named("KeyWriter") ApiKeyWrite writer) {
        return new ApiKeyUpdater(context, writer);
    }

    @Provides
    @Named("KeyWriter")
    ApiKeyWrite writer() {
        return new ApiKeyWrite(context);
    }

    @Provides
    @Named("KeyChecker")
    ApiKeyChecker keyChecker(@Named("ApiKey") ApiKeyManager keyManager, @Named
            ("ApiDialogManager") ApiKeyDialogManager dialogManager) {
        return new ApiKeyChecker(dialogManager, keyManager);
    }
}
