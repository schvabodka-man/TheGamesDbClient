package apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers;

import android.content.Context;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyUpdater;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyWrite;
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
    public ApiKeyManager apiKeyManager() {
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
}
