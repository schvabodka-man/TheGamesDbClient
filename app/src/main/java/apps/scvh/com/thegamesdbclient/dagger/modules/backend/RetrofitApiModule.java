package apps.scvh.com.thegamesdbclient.dagger.modules.backend;

import android.content.Context;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitBuilder;
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
    RetrofitBuilder retrofitInterface() {
        return new RetrofitBuilder(context);
    }

}
