package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/api/GetGame.php")
    Call<GameRawData> getGame(@Query("id") int id);

    @GET("/api/GetGamesList.php")
    Call<List<GameRawData>> getSearchResults(@Query("name") String name);
}
