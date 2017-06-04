package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.GameRawData;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.metadata.RawDeveloper;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.lists.MetadataTypeFlag;
import apps.scvh.com.thegamesdbclient.backend.models.GameDeveloper;
import apps.scvh.com.thegamesdbclient.dagger.comp.Injector;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Additional class for retrieving game metadatq
 */
public class MetadataRetriever extends BroadcastReceiver {

    @Inject
    @Named("RetrofitInterface")
    RetrofitInterface retrofitInterface;

    public MetadataRetriever() {
    }

    /**
     * Instantiates a new Metadata retriever.
     * @param retrofitInterface the retrofit interface
     */
    public MetadataRetriever(RetrofitInterface retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Injector.inject(this, context);
    }

    /**
     * Gets genres.
     * @param genreId the genre id
     * @return the genres
     */
    public ArrayList<String> getGenres(List<Integer> genreId) {
        return retrieveMetadataFromList(genreId, MetadataTypeFlag.GENRES);
    }

    /**
     * Gets engines.
     * @param id the id
     * @return the engines
     */
    public ArrayList<String> getEngines(List<Integer> id) {
        return retrieveMetadataFromList(id, MetadataTypeFlag.ENGINES);
    }

    /**
     * Gets gamemodes.
     * @param id the id
     * @return the gamemodes
     */
    public ArrayList<String> getGamemodes(List<Integer> id) {
        return retrieveMetadataFromList(id, MetadataTypeFlag.GAMEMODES);
    }

    /**
     * Gets perspectives.
     * @param id the id
     * @return the perspectives
     */
    public ArrayList<String> getPerspectives(List<Integer> id) {
        return retrieveMetadataFromList(id, MetadataTypeFlag.PERSPECTIVES);
    }

    /**
     * Gets themes.
     * @param id the id
     * @return the themes
     */
    public ArrayList<String> getThemes(List<Integer> id) {
        return retrieveMetadataFromList(id, MetadataTypeFlag.THEMES);
    }


    /**
     * Here goes generic class that i use with flags to get different arrays of metadata
     */
    private ArrayList<String> retrieveMetadataFromList(List<Integer> id, MetadataTypeFlag flag) {
        Iterator<Integer> iterator = id.iterator();
        ArrayList<String> converted = new ArrayList<>();
        int nextId;
        while (iterator.hasNext()) {
            nextId = iterator.next();
            try {
                converted.add(convertIdToStringData(nextId, flag));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return converted;
    }

    /**
     * And here are stuff for getting metadata with flag
     */
    private String convertIdToStringData(int id, MetadataTypeFlag flag) throws IOException {
        if (flag == MetadataTypeFlag.GENRES) {
            return retrofitInterface.getGenreName(id).execute().body()
                    .get(0).getName();
        } else if (flag == MetadataTypeFlag.ENGINES) {
            return retrofitInterface.getEngine(id).execute().body()
                    .get(0).getName();
        } else if (flag == MetadataTypeFlag.GAMEMODES) {
            return retrofitInterface.getGamemodes(id).execute().body()
                    .get(0).getName();
        } else if (flag == MetadataTypeFlag.PERSPECTIVES) {
            return retrofitInterface.getPerspective(id).execute().body()
                    .get(0).getName();
        } else if (flag == MetadataTypeFlag.THEMES) {
            return retrofitInterface.getThemes(id).execute().body()
                    .get(0).getName();
        } else {
            return "";
        }
    }

    /**
     * Gets data about simillar games.
     * @param id the id
     * @return simillar games
     */
    public GameRawData getRawSimillarGameData(int id) {
        try {
            return retrofitInterface.getLightGameData(id).execute().body().get(0);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Gets light developer metadata.
     * @param id the id
     * @return the light developer metadata
     * @throws IOException the io exception
     */
    public Observable<GameDeveloper> getLightDeveloperMetadata(int id) throws
            IOException {
        return Observable.defer(() -> {
            RawDeveloper developer;
            GameDeveloper developerFinal = new GameDeveloper();
            developer = retrofitInterface.getLightDeveloperData(id).execute().body().get(0);
            if (developer.getName() != null) {
                developerFinal.setName(developer.getName());
            }
            developerFinal.setId(developer.getId());
            return Observable.just(developerFinal);
        }).subscribeOn(Schedulers.newThread());
    }
}
