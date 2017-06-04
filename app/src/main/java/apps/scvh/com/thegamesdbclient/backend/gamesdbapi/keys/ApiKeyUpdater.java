package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;


import android.content.Context;
import android.content.Intent;

import apps.scvh.com.thegamesdbclient.R;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * This class is used for updating api key through dialog window
 */
public class ApiKeyUpdater {

    private Context context;
    private ApiKeyWrite write;

    public ApiKeyUpdater(Context context, ApiKeyWrite write) {
        this.context = context;
        this.write = write;
    }

    /**
     * How does it work:
     * Just sends broadcast if nit get new api key, and then objects that use api key just
     * updating retrofit interface
     * @param newKey observer with key
     */
    public void updateApiKey(Observable<String> newKey) {
        newKey.subscribe(key -> {
            write.writeKey(Observable.just(key).subscribeOn(Schedulers.computation()));
            Intent intent = new Intent();
            intent.setAction(context.getString(R.string.intent_key_changed));
            context.sendBroadcast(intent);
        });
    }
}
