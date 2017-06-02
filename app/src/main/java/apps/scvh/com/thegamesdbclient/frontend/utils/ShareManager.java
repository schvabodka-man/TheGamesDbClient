package apps.scvh.com.thegamesdbclient.frontend.utils;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ShareActionProvider;

import apps.scvh.com.thegamesdbclient.R;

public class ShareManager {

    private Context context;

    public ShareManager(Context context) {
        this.context = context;
    }

    public void setShareIntent(String url, ShareActionProvider actionProvider) {
        if (actionProvider != null) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType(context.getString(R.string.mime_type_for_sharing));
            sharingIntent.putExtra(Intent.EXTRA_TEXT, url);
            actionProvider.setShareIntent(Intent.createChooser(sharingIntent, context.getString(R
                    .string
                    .share_url)));
        }
    }
}
