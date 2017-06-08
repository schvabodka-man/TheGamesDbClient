package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import android.content.Context;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private Context context;
    private ApiKeyManager manager;

    private RetrofitInterface retrofitInterface;

    public RetrofitBuilder(Context context, ApiKeyManager manager) {
        this.context = context;
        this.manager = manager;
    }

    public RetrofitInterface buildRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader(context.getString(R
                    .string.json_key), context.getString(R.string.json_value)).addHeader(context
                            .getString(R.string.api_header),
                    manager.getApiKey().blockingFirst()).build();
            return chain.proceed(request);
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_call))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        return retrofitInterface;
    }
}
