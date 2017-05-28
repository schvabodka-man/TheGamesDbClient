package apps.scvh.com.thegamesdbclient.dagger;

import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.activities.Search;
import dagger.Component;

@Singleton
@Component(modules = {RetrofitApiModule.class, FrontendModule.class})
interface AppDIComponent {
    void inject(Search search);
    void inject(Game game);
}
