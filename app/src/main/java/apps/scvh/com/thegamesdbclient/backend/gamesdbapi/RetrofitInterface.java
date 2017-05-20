package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("")
    Call<GameRawData> getGame(@Query("") int id);

    @GET("")
    Call<List<GameRawData>> getSearchResults(@Query("") String name);
}
