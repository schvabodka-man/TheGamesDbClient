package apps.scvh.com.thegamesdbclient.dagger.comp;

import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.dagger.modules.backend.RetrofitApiModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.ConvertersModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.KeysModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.RetrieversModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.frontend.FrontendDialogsModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.frontend.FrontendInjectorsModule;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Developer;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Search;
import dagger.Component;


/**
 * Main interface used for injecting. Here goes ton of equal methods for injecting
 */
@Singleton
@Component(modules = {RetrofitApiModule.class, KeysModule.class,
        ConvertersModule.class, RetrieversModule.class, FrontendInjectorsModule.class,
        FrontendDialogsModule.class})
interface AppDIComponent {

    /**
     * Inject.
     * @param search the search activity
     */
    void inject(Search search);

    /**
     * Inject.
     * @param game the game activity
     */
    void inject(Game game);

    /**
     * Inject.
     * @param developer the developer activity
     */
    void inject(Developer developer);

}
