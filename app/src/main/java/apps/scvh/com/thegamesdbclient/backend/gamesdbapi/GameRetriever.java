package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.ArrayList;

import apps.scvh.com.thegamesdbclient.backend.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GameRetriever {

    private RetrofitInterface api;
    private ApiKeyManager manager;
    private RawDataConverter converter;

    public GameRetriever(RetrofitInterface api, ApiKeyManager manager, RawDataConverter converter) {
        this.api = api;
        this.manager = manager;
        this.converter = converter;
    }

    public Observable<GameData> getGame(final int id) {
        return Observable.defer(() -> Observable.just(converter.convertRawData(api.getGame(id)
                .execute().body().get(0)))).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread());
    }


    public Observable<ArrayList<GameData>> searchGames(final String searchQuery) {
        return Observable.defer(() -> Observable.just(converter.convertRawSearch(api
                .getSearchResults
                (searchQuery).execute().body()))).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread());
    }
}
