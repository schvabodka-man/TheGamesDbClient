package apps.scvh.com.thegamesdbclient.dagger.comp;


import android.content.Context;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.RetrofitApiModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.ConvertersModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.KeysModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.RetrieversModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.frontend.FrontendDialogsModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.frontend.FrontendInjectorsModule;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Developer;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Search;

public class Injector {

    public static void inject(Search search) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(search.getBaseContext())
        ).frontendInjectorsModule(new FrontendInjectorsModule(search)).frontendDialogsModule(new
                FrontendDialogsModule(search)).convertersModule(new ConvertersModule(search))
                .keysModule(new KeysModule(search)).retrieversModule(new RetrieversModule())
                .build();
        component.inject(search);
    }

    public static void inject(Game game) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(game.getBaseContext())
        ).frontendInjectorsModule(new FrontendInjectorsModule(game)).frontendDialogsModule(new
                FrontendDialogsModule(game)).convertersModule(new ConvertersModule(game))
                .keysModule(new KeysModule(game)).retrieversModule(new RetrieversModule()).build();
        component.inject(game);
    }

    public static void inject(Developer developer) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(developer.getBaseContext())
        ).frontendInjectorsModule(new FrontendInjectorsModule(developer)).frontendDialogsModule(new
                FrontendDialogsModule(developer)).convertersModule(new ConvertersModule(developer))
                .keysModule(new KeysModule(developer)).retrieversModule(new RetrieversModule())
                .build();
        component.inject(developer);
    }

    public static void inject(MetadataRetriever retriever, Context context) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(context)
        ).frontendInjectorsModule(new FrontendInjectorsModule()).frontendDialogsModule(new
                FrontendDialogsModule()).convertersModule(new ConvertersModule(context))
                .keysModule(new KeysModule(context)).retrieversModule(new RetrieversModule())
                .build();
        component.inject(retriever);
    }

    public static void inject(GameRetriever retriever, Context context) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(context)).frontendInjectorsModule(new FrontendInjectorsModule()
        ).frontendDialogsModule(new
                FrontendDialogsModule()).convertersModule(new ConvertersModule(context))
                .keysModule(new KeysModule(context)).retrieversModule(new RetrieversModule())
                .build();
        component.inject(retriever);
    }

}
