package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.List;

import retrofit2.Call;

public class GameRetriever {


    private RetrofitInterface api;

    public GameRetriever(RetrofitInterface api) {
        this.api = api;
    }

    public Call<GameRawData> getGame(int id) {
        return api.getGame(id);
    }

    public Call<List<GameRawData>> searchGames(String searchQuery) {
        return api.getSearchResults(searchQuery);
    }
}
