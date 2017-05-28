package apps.scvh.com.thegamesdbclient.dagger;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RawDataConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
class RetrofitApiModule {

    private Context context;

    RetrofitApiModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("ApiKey")
    ApiKeyManager apiKeyManager() {
        return new ApiKeyManager(context);
    }

    @Provides
    @Named("GameDataConverter")
    RawDataConverter dataConverter(@Named("MetadataRetriever")
                                           MetadataRetriever retriever) {
        return new RawDataConverter(context, retriever);
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
        return retrofit.create(RetrofitInterface.class);
    }

    @Provides
    @Named("GameRetriever")
    GameRetriever retriever(@Named("RetrofitInterface") RetrofitInterface retrofitInterface,
                            @Named("GameDataConverter") RawDataConverter rawDataConverter) {
        return new GameRetriever(retrofitInterface, rawDataConverter);
    }

    @Provides
    @Named("MetadataRetriever")
    MetadataRetriever metadataRetriever(@Named("RetrofitInterface") RetrofitInterface
                                                retrofitInterface) {
        return new MetadataRetriever(retrofitInterface);
    }

}
