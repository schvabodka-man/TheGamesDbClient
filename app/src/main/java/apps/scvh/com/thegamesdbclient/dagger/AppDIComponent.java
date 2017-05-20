package apps.scvh.com.thegamesdbclient.dagger;

import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.frontend.layouts.Search;
import dagger.Component;

@Singleton
@Component(modules = {RetrofitApiModule.class})
public interface AppDIComponent {
    void inject(Search search);
}
