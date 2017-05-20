package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.ArrayList;
import java.util.concurrent.Callable;

import apps.scvh.com.thegamesdbclient.backend.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.GameData;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class GameRetriever {

    private RetrofitInterface api;
    private ApiKeyManager manager;
    private RawDataConverter converter;

    public GameRetriever(RetrofitInterface api) {
        this.api = api;
    }

    public Observable<GameData> getGame(final int id) {
        return Observable.defer(() -> {
            String apiKey = manager.getApiKey();
            return Observable.just(converter.convertRawData(api.getGame(id).execute().body()));
        }).subscribeOn(Schedulers.io());
    }


    public Observable<ArrayList<GameData>> searchGames(final String searchQuery) {
        return Observable.defer(() -> {
            String apiKey = manager.getApiKey();
            return Observable.just(converter.convertRawSearch(api.getSearchResults
                    (searchQuery).execute().body()));
        }).subscribeOn(Schedulers.io());
    }
}
