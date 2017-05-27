package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.ArrayList;
import java.util.List;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.GameRawData;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.RawGenre;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/games/{Id}?fields=*")
    Call<List<GameRawData>> getGame(@Path("Id") int id);

    @GET("/games/?fields=name,summary,cover&limit=50")
    Call<ArrayList<GameRawData>> getSearchResults(@Query("search") String name);

    @GET("/genres/{Id}/?fields=name")
    Call<List<RawGenre>> getGenreName(@Path("Id") int id);

}
