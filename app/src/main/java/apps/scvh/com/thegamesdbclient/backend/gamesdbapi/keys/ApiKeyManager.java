package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys;


import android.content.Context;

import com.securepreferences.SecurePreferences;

import apps.scvh.com.thegamesdbclient.R;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * This class is used for getting api keys from encrypted preferences
 */
public class ApiKeyManager {

    private Context context;

    public ApiKeyManager(Context context) {
        this.context = context;
    }

    public Observable<String> getApiKey() {
        return Observable.defer(() -> Observable.just(new SecurePreferences(context).getString
                (context.getString
                        (R.string.api_key_setting), ""))).subscribeOn(Schedulers.newThread());
    }
}
