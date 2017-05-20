package apps.scvh.com.thegamesdbclient.dagger;


import apps.scvh.com.thegamesdbclient.frontend.layouts.Search;

public class Injector {

    public static void inject(Search search) {
        AppDIComponent component = DaggerAppDIComponent.builder().retrofitApiModule(new
                RetrofitApiModule(search.getBaseContext())
        ).build();
        component.inject(search);
    }

}
