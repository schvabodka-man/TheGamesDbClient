package apps.scvh.com.thegamesdbclient.dagger;

import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.frontend.layouts.Search;

@Singleton
public interface AppDaggerComponent {
    void inject(Search search);
}
