package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;


import android.content.Context;
import android.content.Intent;

import apps.scvh.com.thegamesdbclient.R;

public class ApiKeyUpdater {

    private Context context;

    public ApiKeyUpdater(Context context) {
        this.context = context;
    }

    public void updateApiKey() {
        Intent intent = new Intent();
        intent.setAction(context.getString(R.string.intent_key_changed));
        context.sendBroadcast(intent);
    }
}
