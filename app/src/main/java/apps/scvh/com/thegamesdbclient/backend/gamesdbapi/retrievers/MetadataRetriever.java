package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;

public class MetadataRetriever {

    private RetrofitInterface retrofitInterface;

    public MetadataRetriever(RetrofitInterface retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    public ArrayList<String> getGenres(List<Integer> genreId) {
        Iterator<Integer> genres = genreId.iterator();
        ArrayList<String> genresConverted = new ArrayList<>();
        int nextGenreId;
        while (genres.hasNext()) {
            nextGenreId = genres.next();
            try {
                genresConverted.add(retrofitInterface.getGenreName(nextGenreId).execute().body()
                        .get(0).getName()); // No, it won't produce null pointer because if there
                // are no genres set this code will not be called ;)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return genresConverted;
    }

    public ArrayList<String> getEngines(List<Integer> id) {
        Iterator<Integer> engines = id.iterator();
        ArrayList<String> engineConverted = new ArrayList<>();
        int nextEngineId;
        while (engines.hasNext()) {
            nextEngineId = engines.next();
            try {
                engineConverted.add(retrofitInterface.getEngine(nextEngineId).execute().body()
                        .get(0).getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return engineConverted;
    }
}
