package apps.scvh.com.thegamesdbclient.dagger.modules.frontend;


import android.app.Activity;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.frontend.activities.Developer;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.injectors.DeveloperInjector;
import apps.scvh.com.thegamesdbclient.frontend.injectors.GameViewsInjector;
import dagger.Module;
import dagger.Provides;

@Module
public class FrontendInjectorsModule {

    private Activity activity;

    public FrontendInjectorsModule(Activity activity) {
        this.activity = activity;
    }

    public FrontendInjectorsModule() {
    }

    @Provides
    @Named("ViewsInjector")
    public GameViewsInjector viewsInjector() {
        return new GameViewsInjector((Game) activity);
    }

    @Provides
    @Named("DeveloperInjector")
    public DeveloperInjector developerInjector() {
        return new DeveloperInjector((Developer) activity);
    }


}
