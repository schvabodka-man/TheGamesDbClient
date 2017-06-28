package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitBuilder;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.DeveloperConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.RawDataConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.metadata.RawDeveloper;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.backend.models.GameDeveloper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Simple class for retrieving game data from api
 */
public class GameRetriever extends BroadcastReceiver {

    private RetrofitInterface api;
    private RetrofitBuilder builder;
    private RawDataConverter converter;
    private DeveloperConverter developerConverter;
    private ApiKeyManager keyManager;

    private String apiKeyFlag; //not so nice trick but i'm pretty ok with that

    public GameRetriever() {
    }

    public GameRetriever(RetrofitBuilder builder, RawDataConverter converter, DeveloperConverter
            developerConverter, ApiKeyManager keyManager) {
        this.builder = builder;
        this.converter = converter;
        this.developerConverter = developerConverter;
        this.api = this.builder.buildRetrofit();
        this.keyManager = keyManager;
        apiKeyFlag = keyManager.getApiKey().blockingFirst();
    }

    /**
     * And this is here for updating api token. Basically it's just reinjecting on receiving
     * broadcast
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        apiKeyFlag = keyManager.getApiKey().blockingFirst();
    }

    public Observable<GameData> getGame(final int id) {
        return Observable.defer(() -> Observable.just(converter.convertRawData(api.getGame(id,
                apiKeyFlag)
                .execute().body().get(0)))).subscribeOn(Schedulers.newThread());
    }

    public Observable<ArrayList<GameData>> searchGames(final String searchQuery) {
        return Observable.defer(() -> Observable.just(converter.convertRawSearch(api
                .getSearchResults
                        (searchQuery, apiKeyFlag).execute().body()))).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread());
    }

    public Observable<GameDeveloper> getDeveloper(final int id) {
        return Observable.defer(() -> {
            RawDeveloper rawDeveloper = api.getDeveloper(id, apiKeyFlag).execute().body().get(0);
            return Observable.just(developerConverter.convertDeveloper(rawDeveloper));
        }).subscribeOn(Schedulers.newThread());
    }
}
