package apps.scvh.com.thegamesdbclient.dagger.modules.frontend;


import android.app.Activity;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.frontend.dialogs.ApiKeyDialogManager;
import apps.scvh.com.thegamesdbclient.frontend.injectors.DeveloperInjector;
import apps.scvh.com.thegamesdbclient.frontend.injectors.GameViewsInjector;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Developer;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.utils.MenuManager;
import apps.scvh.com.thegamesdbclient.frontend.utils.RecyclerViewWorker;
import apps.scvh.com.thegamesdbclient.frontend.utils.ShareManager;
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
    DeveloperInjector developerInjector() {
        return new DeveloperInjector((Developer) activity);
    }

    @Provides
    @Named("RecyclerProvider")
    RecyclerViewWorker recyclerViewGetter() {
        return new RecyclerViewWorker(activity);
    }

    @Provides
    @Named("ShareManager")
    ShareManager shareManager() {
        return new ShareManager(activity);
    }

    @Provides
    @Named("MenuManager")
    MenuManager menuManager(@Named("ApiDialogManager")
                                           ApiKeyDialogManager dialogManager) {
        return new MenuManager(dialogManager, activity);
    }

}
