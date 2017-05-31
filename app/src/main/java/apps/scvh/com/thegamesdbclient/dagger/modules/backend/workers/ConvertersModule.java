package apps.scvh.com.thegamesdbclient.dagger.modules.backend.workers;

import android.content.Context;

import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.DeveloperConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.RawDataConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import dagger.Module;
import dagger.Provides;

@Module
public class ConvertersModule {

    private Context context;

    public ConvertersModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("DeveloperConverter")
    DeveloperConverter developerConverter() {
        return new DeveloperConverter();
    }

    @Provides
    @Named("GameDataConverter")
    public RawDataConverter dataConverter(@Named("MetadataRetriever")
                                                  MetadataRetriever retriever) {
        return new RawDataConverter(context, retriever);
    }

}
