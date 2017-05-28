package apps.scvh.com.thegamesdbclient.frontend.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import apps.scvh.com.thegamesdbclient.R;

public class Settings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        addPreferencesFromResource(R.xml.settings);
    }

}
