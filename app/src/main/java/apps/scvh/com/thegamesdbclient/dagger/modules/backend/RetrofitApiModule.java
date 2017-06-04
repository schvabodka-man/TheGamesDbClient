package apps.scvh.com.thegamesdbclient.dagger.modules.backend;

import android.content.Context;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitApiModule {

    private Context context;

    public RetrofitApiModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("RetrofitInterface")
    public RetrofitInterface retrofitInterface(@Named("ApiKey") ApiKeyManager manager) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader(context.getString(R
                    .string.json_key), context.getString(R.string.json_value)).addHeader(context
                            .getString(R.string.api_header),
                    manager.getApiKey().blockingFirst()).build();
            return chain.proceed(request);
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_call))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitInterface.class);
    }

}
