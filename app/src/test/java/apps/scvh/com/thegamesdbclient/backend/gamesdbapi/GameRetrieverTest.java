package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import android.app.Application;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class GameRetrieverTest extends Application {

    private GameRetriever retriever;

    public GameRetrieverTest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://thegamesdb.net")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        RetrofitInterface api = retrofit.create(RetrofitInterface.class);
        retriever = new GameRetriever(api);
    }

    @Test
    public void testGettingGame() {
        int gameId = 4;
        try {
            GameRawData results = retriever.getGame(gameId).execute().body();
            Assert.assertEquals("Star Fox 64", results.getGameTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
