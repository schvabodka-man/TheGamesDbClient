package apps.scvh.com.thegamesdbclient.dagger.modules;


import android.app.Activity;

import javax.inject.Named;
import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.frontend.GameViewsInjector;
import apps.scvh.com.thegamesdbclient.frontend.LoadingDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class FrontendModule {

    private Activity activity;

    public FrontendModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Named("ViewsInjector")
    public GameViewsInjector viewsInjector() {
        return new GameViewsInjector((Game) activity);
    }

    @Provides
    @Named("DialogManager")
    LoadingDialogManager dialogManager() {
        return new LoadingDialogManager(activity.getBaseContext());
    }
}
