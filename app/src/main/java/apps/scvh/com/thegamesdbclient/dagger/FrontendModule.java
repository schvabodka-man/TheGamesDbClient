package apps.scvh.com.thegamesdbclient.dagger;


import javax.inject.Named;
import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.frontend.GameViewsInjector;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import dagger.Module;
import dagger.Provides;

@Module
@Singleton
class FrontendModule {

    private Game game;

    FrontendModule(Game game) {
        this.game = game;
    }

    FrontendModule() {
    }

    @Provides
    @Named("ViewsInjector")
    GameViewsInjector viewsInjector() {
        return new GameViewsInjector(game);
    }
}
