package apps.scvh.com.thegamesdbclient.dagger.comp;


import android.content.Context;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import apps.scvh.com.thegamesdbclient.dagger.modules.FrontendModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.RetrofitApiModule;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.activities.Search;

public class Injector {

    public static void inject(Search search) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(search.getBaseContext())
        ).frontendModule(new FrontendModule(search)).build();
        component.inject(search);
    }

    public static void inject(Game game) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(game.getBaseContext())
        ).frontendModule(new FrontendModule(game)).build();
        component.inject(game);
    }

    public static void inject(MetadataRetriever retriever, Context context) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(context)
        ).frontendModule(new FrontendModule()).build();
        component.inject(retriever);
    }

    public static void inject(GameRetriever retriever, Context context) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(context)
        ).frontendModule(new FrontendModule()).build();
        component.inject(retriever);
    }
}
