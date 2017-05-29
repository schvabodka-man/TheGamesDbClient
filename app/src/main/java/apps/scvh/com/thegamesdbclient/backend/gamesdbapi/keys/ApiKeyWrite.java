package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;


import android.content.Context;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

import apps.scvh.com.thegamesdbclient.R;

public class ApiKeyWrite {

    private Context context;

    public ApiKeyWrite(Context context) {
        this.context = context;
    }

    void writeKey(String key) {
        SharedPreferences preferences = new SecurePreferences(context);
        preferences.edit().putString(context.getString(R.string.api_key_setting), key).apply();
    }
}
