package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;


import android.content.Context;
import android.content.Intent;

import apps.scvh.com.thegamesdbclient.R;

public class ApiKeyUpdater {

    private Context context;
    private ApiKeyWrite write;

    public ApiKeyUpdater(Context context, ApiKeyWrite write) {
        this.context = context;
        this.write = write;
    }

    public void updateApiKey(String newKey) {
        write.writeKey(newKey);
        Intent intent = new Intent();
        intent.setAction(context.getString(R.string.intent_key_changed));
        context.sendBroadcast(intent);
    }
}
