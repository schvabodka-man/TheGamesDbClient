package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import android.content.Context;

import apps.scvh.com.thegamesdbclient.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private Context context;

    public RetrofitBuilder(Context context) {
        this.context = context;
    }

    public RetrofitInterface buildRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_call))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitInterface.class);
    }
}
