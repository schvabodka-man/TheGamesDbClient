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
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.lists.MetadataTypeFlag;
import apps.scvh.com.thegamesdbclient.dagger.comp.Injector;

public class MetadataRetriever extends BroadcastReceiver {

    @Inject
    @Named("RetrofitInterface")
    RetrofitInterface retrofitInterface;

    @Override
    public void onReceive(Context context, Intent intent) {
        Injector.inject(this, context);
    }

    public MetadataRetriever() {
    }

    public MetadataRetriever(RetrofitInterface retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    public ArrayList<String> getGenres(List<Integer> genreId) {
        return retrieveMetadataFromList(genreId, MetadataTypeFlag.GENRES);
    }

    public ArrayList<String> getEngines(List<Integer> id) {
        return retrieveMetadataFromList(id, MetadataTypeFlag.ENGINES);
    }

    public ArrayList<String> getGamemodes(List<Integer> id) {
        return retrieveMetadataFromList(id, MetadataTypeFlag.GAMEMODES);
    }

    public ArrayList<String> getPerspectives(List<Integer> id) {
        return retrieveMetadataFromList(id, MetadataTypeFlag.PERSPECTIVES);
    }

    public ArrayList<String> getThemes(List<Integer> id) {
        return retrieveMetadataFromList(id, MetadataTypeFlag.THEMES);
    }

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

    public GameRawData getRawSimillarGameData(int id) {
        try {
            return retrofitInterface.getLightGameData(id).execute().body().get(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
