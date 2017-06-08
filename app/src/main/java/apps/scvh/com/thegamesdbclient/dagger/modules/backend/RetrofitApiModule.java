package apps.scvh.com.thegamesdbclient.dagger.modules.backend;

import android.content.Context;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitBuilder;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitApiModule {

    private Context context;

    public RetrofitApiModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("RetrofitInterfaceBuilder")
    public RetrofitBuilder retrofitInterface(@Named("ApiKey") ApiKeyManager manager) {
        return new RetrofitBuilder(context, manager);
    }

}
