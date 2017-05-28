package apps.scvh.com.thegamesdbclient.dagger.modules;


import javax.inject.Named;
import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.frontend.GameViewsInjector;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class FrontendModule {

    private Game game;

    public FrontendModule(Game game) {
        this.game = game;
    }

    public FrontendModule() {
    }

    @Provides
    @Named("ViewsInjector")
    public GameViewsInjector viewsInjector() {
        return new GameViewsInjector(game);
    }
}
