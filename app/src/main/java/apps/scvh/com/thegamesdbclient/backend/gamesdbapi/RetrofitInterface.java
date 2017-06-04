package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.ArrayList;
import java.util.List;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.GameRawData;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.metadata.RawDeveloper;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.metadata.RawMetadata;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/games/{Id}?fields=*")
    Call<List<GameRawData>> getGame(@Path("Id") int id);

    @GET("/games/?fields=name,summary,cover&limit=50")
    Call<ArrayList<GameRawData>> getSearchResults(@Query("search") String name);

    @GET("/games/{Id}?fields=id,name,cover")
    Call<List<GameRawData>> getLightGameData(@Path("Id") int id);

    @GET("/genres/{Id}/?fields=name")
    Call<List<RawMetadata>> getGenreName(@Path("Id") int id);

    @GET("/companies/{Id}/?fields=*")
    Call<List<RawDeveloper>> getDeveloper(@Path("Id") int id);

    @GET("/companies/{Id}/?fields=name,id")
    Call<List<RawDeveloper>> getLightDeveloperData(@Path("Id") int id);

    @GET("/game_engines/{Id}/?fields=name")
    Call<List<RawMetadata>> getEngine(@Path("Id") int id);

    @GET("/player_perspectives/{Id}/?fields=name")
    Call<List<RawMetadata>> getPerspective(@Path("Id") int id);

    @GET("/themes/{Id}/?fields=name")
    Call<List<RawMetadata>> getThemes(@Path("Id") int id);

    @GET("/game_modes/{Id}/?fields=name")
    Call<List<RawMetadata>> getGamemodes(@Path("Id") int id);

}
