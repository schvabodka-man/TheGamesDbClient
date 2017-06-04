package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.RetrofitInterface;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.DeveloperConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters.RawDataConverter;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.metadata.RawDeveloper;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.backend.models.GameDeveloper;
import apps.scvh.com.thegamesdbclient.dagger.comp.Injector;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GameRetriever extends BroadcastReceiver {

    @Inject
    @Named("RetrofitInterface")
    RetrofitInterface api;
    private RawDataConverter converter;
    private DeveloperConverter developerConverter;

    @Override
    public void onReceive(Context context, Intent intent) {
        Injector.inject(this, context);
    }

    public GameRetriever() {
    }

    public GameRetriever(RetrofitInterface api, RawDataConverter converter, DeveloperConverter
            developerConverter) {
        this.api = api;
        this.converter = converter;
        this.developerConverter = developerConverter;
    }

    public Observable<GameData> getGame(final int id) {
        return Observable.defer(() -> Observable.just(converter.convertRawData(api.getGame(id)
                .execute().body().get(0)))).subscribeOn(Schedulers.newThread());
    }

    public Observable<ArrayList<GameData>> searchGames(final String searchQuery) {
        return Observable.defer(() -> Observable.just(converter.convertRawSearch(api
                .getSearchResults
                        (searchQuery).execute().body()))).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread());
    }

    public Observable<GameDeveloper> getDeveloper(final int id) {
        return Observable.defer(() -> {
            RawDeveloper rawDeveloper = api.getDeveloper(id).execute().body().get(0);
            return Observable.just(developerConverter.convertDeveloper(rawDeveloper));
        }).subscribeOn(Schedulers.newThread());
    }
}
