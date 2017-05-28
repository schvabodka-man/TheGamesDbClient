package apps.scvh.com.thegamesdbclient.dagger;


import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.activities.Search;

public class Injector {

    public static void inject(Search search) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(search.getBaseContext())
        ).frontendModule(new FrontendModule()).build();
        component.inject(search);
    }

    public static void inject(Game game) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(game.getBaseContext())
        ).frontendModule(new FrontendModule(game)).build();
        component.inject(game);
    }
}
