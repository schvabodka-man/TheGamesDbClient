package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;

import java.util.List;

import io.reactivex.Observable;

public class GameRetriever {

    private RetrofitInterface api;

    public GameRetriever(RetrofitInterface api) {
        this.api = api;
    }

    public Observable<GameRawData> getGame(final int id) {
        return Observable.defer(() -> Observable.just(api.getGame(id).execute().body()));
    }

    public Observable<List<GameRawData>> searchGames(String searchQuery) {
        return Observable.defer(() -> Observable.just(api.getSearchResults(searchQuery).execute().body()));
    }
}
