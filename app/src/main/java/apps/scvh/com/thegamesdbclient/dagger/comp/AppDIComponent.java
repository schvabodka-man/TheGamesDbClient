package apps.scvh.com.thegamesdbclient.dagger.comp;

import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import apps.scvh.com.thegamesdbclient.dagger.modules.FrontendModule;
import apps.scvh.com.thegamesdbclient.dagger.modules.RetrofitApiModule;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.activities.Search;
import dagger.Component;

@Singleton
@Component(modules = {RetrofitApiModule.class, FrontendModule.class})
public interface AppDIComponent {
    void inject(Search search);
    void inject(Game game);
    void inject(MetadataRetriever retriever);
    void inject(GameRetriever retriever);
}
