package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;


import android.content.Context;


public class ApiKeyManager {

    private Context context;

    public ApiKeyManager(Context context) {
        this.context = context;
    }

    public String getApiKey() {
//         return PreferenceManager.getDefaultSharedPreferences(context).getString(context
// .getString(R
//                .string.api_key_setting), "");
        return "TXWtLk4wtEmshHdqh80affyr0AcRp1wYMfyjsnaqCHJEKoGNIl";
    }
}
