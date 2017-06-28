package apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers;


import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitBuilder;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.DeveloperConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.RawDataConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import dagger.Module;
import dagger.Provides;

@Module
public class RetrieversModule {

    @Provides
    @Named("GameRetriever")
    public GameRetriever retriever(@Named("RetrofitInterfaceBuilder") RetrofitBuilder builder,
                                   @Named("GameDataConverter") RawDataConverter rawDataConverter,
                                   @Named("DeveloperConverter") DeveloperConverter
                                           developerConverter,
                                   @Named("ApiKey") ApiKeyManager keyManager) {
        return new GameRetriever(builder, rawDataConverter, developerConverter, keyManager);
    }

    @Provides
    @Named("MetadataRetriever")
    MetadataRetriever metadataRetriever(@Named("RetrofitInterfaceBuilder") RetrofitBuilder
                                                builder, @Named("ApiKey") ApiKeyManager
            keyManager) {
        return new MetadataRetriever(builder, keyManager);
    }

}
