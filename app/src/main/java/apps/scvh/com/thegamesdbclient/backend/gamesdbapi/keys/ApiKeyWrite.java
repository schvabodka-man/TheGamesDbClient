package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;


import android.content.Context;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

import apps.scvh.com.thegamesdbclient.R;
import io.reactivex.Observable;

public class ApiKeyWrite {

    private Context context;

    public ApiKeyWrite(Context context) {
        this.context = context;
    }

    void writeKey(Observable<String> keyObservable) {
        keyObservable.subscribe(key -> {
            SharedPreferences preferences = new SecurePreferences(context);
            preferences.edit().putString(context.getString(R.string.api_key_setting), key).apply();
        });
    }
}
