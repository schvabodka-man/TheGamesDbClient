package apps.scvh.com.thegamesdbclient.frontend.dialogs;


import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyManager;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.keys.ApiKeyUpdater;

public class ApiKeyDialogManager {

    private Activity activity;
    private ApiKeyUpdater updater;
    private ApiKeyManager getter;

    private AlertDialog dialog;

    public ApiKeyDialogManager(Activity activity, ApiKeyUpdater updater, ApiKeyManager getter) {
        this.activity = activity;
        this.updater = updater;
        this.getter = getter;
    }

    public void showDialog() {
        dialog = buildDialog(activity, getter.getApiKey());
        dialog.show();
    }

    private void closeDialog() {
        dialog.dismiss();
    }

    private AlertDialog buildDialog(Activity activity, String key) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setTitle(activity.getBaseContext().getString(R.string.enter_api_key));
        dialogBuilder.setView(generateDialogView(activity, key));
        return dialogBuilder.create();
    }

    private View generateDialogView(Activity activity, String key) {
        View view = LayoutInflater.from(activity).inflate(R.layout.api_key_dialog, null); //no
        // parent really
        EditText text = (EditText) view.findViewById(R.id.api_key);
        text.setText(key);
        initButtonClickListener((Button) view.findViewById(R.id.submit_api), text);
        return view;
    }

    private void initButtonClickListener(Button button, EditText text) {
        button.setOnClickListener(v -> {
            updater.updateApiKey(text.getText().toString());
            closeDialog();
        });
    }

}
