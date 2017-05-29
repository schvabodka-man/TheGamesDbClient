package apps.scvh.com.thegamesdbclient.frontend.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyUpdater;
import apps.scvh.com.thegamesdbclient.dagger.comp.Injector;

public class Settings extends PreferenceActivity {

    @Inject
    @Named("KeyUpdater")
    ApiKeyUpdater updater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        addPreferencesFromResource(R.xml.settings);
        Injector.inject(this);
    }

}
