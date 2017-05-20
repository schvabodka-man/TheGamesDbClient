package apps.scvh.com.thegamesdbclient.dagger;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RawDataConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class RetrofitApiModule {

    private Context context;

    public RetrofitApiModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("ApiKey")
    ApiKeyManager apiKeyManager() {
        return new ApiKeyManager(context);
    }

    @Provides
    @Named("GameDataConverter")
    RawDataConverter dataConverter() {
        return new RawDataConverter();
    }

    @Provides
    @Named("RetrofitInterface")
    RetrofitInterface retrofitInterface() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader(context.getString(R
                    .string.json_key), context.getString(R.string.json_value)).build();
            return chain.proceed(request);
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_call))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface api = retrofit.create(RetrofitInterface.class);
        return api;
    }

    @Provides
    @Named("GameRetriever")
    GameRetriever retriever(@Named("RetrofitInterface") RetrofitInterface retrofitInterface,
                            @Named("ApiKey") ApiKeyManager keyManager,
                            @Named("GameDataConverter") RawDataConverter rawDataConverter) {
        return new GameRetriever(retrofitInterface, keyManager, rawDataConverter);
    }
}
