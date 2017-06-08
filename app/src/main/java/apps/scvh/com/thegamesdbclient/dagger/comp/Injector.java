package apps.scvh.com.thegamesdbclient.dagger.comp;


import apps.scvh.com.thegamesdbclient.dagger.modules.backend.RetrofitApiModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.ConvertersModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.KeysModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.RetrieversModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.frontend.FrontendDialogsModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.frontend.FrontendInjectorsModule;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Developer;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Search;

/**
 * And this is for injecting too. Don't look on "same" methods please, this is how dagger works(i
 * can't pass just activity as argument, i MUST pass search activiy or game activity, etc for
 * method)
 * I'm using static methods for less hassle
 */
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

}
