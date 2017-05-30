package apps.scvh.com.thegamesdbclient.dagger.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.DeveloperConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.RawDataConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyUpdater;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyWrite;
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
public class RetrofitApiModule {

    private Context context;

    public RetrofitApiModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("ApiKey")
    public ApiKeyManager apiKeyManager() {
        return new ApiKeyManager(context);
    }

    @Provides
    @Named("GameDataConverter")
    public RawDataConverter dataConverter(@Named("MetadataRetriever")
                                                  MetadataRetriever retriever) {
        return new RawDataConverter(context, retriever);
    }

    @Provides
    @Named("RetrofitInterface")
    public RetrofitInterface retrofitInterface() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader(context.getString(R
                    .string.json_key), context.getString(R.string.json_value)).addHeader(context
                            .getString(R.string.api_header),
                    apiKeyManager().getApiKey().blockingFirst()).build();
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
    public GameRetriever retriever(@Named("RetrofitInterface") RetrofitInterface retrofitInterface,
                                   @Named("GameDataConverter") RawDataConverter rawDataConverter,
                                   @Named("DeveloperConverter") DeveloperConverter
                                               developerConverter) {
        return new GameRetriever(retrofitInterface, rawDataConverter, developerConverter);
    }

    @Provides
    @Named("MetadataRetriever")
    public MetadataRetriever metadataRetriever(@Named("RetrofitInterface") RetrofitInterface
                                                       retrofitInterface) {
        return new MetadataRetriever(retrofitInterface);
    }

    @Provides
    @Named("KeyUpdater")
    ApiKeyUpdater updater(@Named("KeyWriter") ApiKeyWrite writer) {
        return new ApiKeyUpdater(context, writer);
    }

    @Provides
    @Named("KeyWriter")
    ApiKeyWrite writer() {
        return new ApiKeyWrite(context);
    }

    @Provides
    @Named("DeveloperConverter")
    DeveloperConverter developerConverter() {
        return new DeveloperConverter();
    }

}
