package apps.scvh.com.thegamesdbclient.dagger.comp;

import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.RetrofitApiModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.ConvertersModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.KeysModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers.RetrieversModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.frontend.FrontendDialogsModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.frontend.FrontendInjectorsModule;
import apps.scvh.com.thegamesdbclient.frontend.activities.Developer;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.activities.Search;
import dagger.Component;

@Singleton
@Component(modules = {RetrofitApiModule.class, KeysModule.class,
        ConvertersModule.class, RetrieversModule.class, FrontendInjectorsModule.class,
        FrontendDialogsModule.class})
public interface AppDIComponent {

    void inject(Search search);
    void inject(Game game);
    void inject(Developer developer);

    void inject(MetadataRetriever retriever);
    void inject(GameRetriever retriever);
}
