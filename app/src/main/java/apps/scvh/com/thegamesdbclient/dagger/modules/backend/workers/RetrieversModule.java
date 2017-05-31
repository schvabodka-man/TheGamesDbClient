package apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers;


import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.DeveloperConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.RawDataConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import dagger.Module;
import dagger.Provides;

@Module
public class RetrieversModule {

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

}
