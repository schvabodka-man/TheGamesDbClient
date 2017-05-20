package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/games/{Id}?fields=*")
    Call<List<GameRawData>> getGame(@Path("Id") int id);

    @GET("")
    Call<List<GameRawData>> getSearchResults(@Query("") String name);
}
