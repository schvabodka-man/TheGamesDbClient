package apps.scvh.com.thegamesdbclient.frontend;


import android.app.ProgressDialog;
import android.content.Context;

import apps.scvh.com.thegamesdbclient.R;

public class LoadingDialogManager {

    private ProgressDialog dialog;
    private Context context;

    public LoadingDialogManager(Context context) {
        this.context = context;
    }

    public void showDialog(ProgressDialog dialog) {
        this.dialog = dialog;
        this.dialog.setTitle(context.getString(R.string.loading));
        this.dialog.setMessage(context.getString(R.string.please_wait));
        this.dialog.show();
    }

    public void hideDialog() {
        dialog.dismiss();
    }

    public ProgressDialog getDialog() {
        return dialog;
    }
}
