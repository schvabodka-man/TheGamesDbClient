package apps.scvh.com.thegamesdbclient.frontend.dialogs;


import android.app.Activity;
import android.app.ProgressDialog;

import apps.scvh.com.thegamesdbclient.R;

public class LoadingDialogManager {

    private ProgressDialog dialog;
    private Activity activity;

    public LoadingDialogManager(Activity activity) {
        this.activity = activity;
    }

    public void showDialog(ProgressDialog dialog) {
        basicDialog(dialog);
    }

    public void showDialogWithClickListener(ProgressDialog dialog) {
        dialog.setOnCancelListener(dialog1 -> activity.finish());
        basicDialog(dialog);
    }

    public void hideDialog() {
        dialog.dismiss();
    }

    public ProgressDialog getDialog() {
        return dialog;
    }

    private void basicDialog(ProgressDialog dialog) {
        this.dialog = dialog;
        this.dialog.setTitle(activity.getString(R.string.loading));
        this.dialog.setMessage(activity.getString(R.string.please_wait));
        this.dialog.show();
    }
}
