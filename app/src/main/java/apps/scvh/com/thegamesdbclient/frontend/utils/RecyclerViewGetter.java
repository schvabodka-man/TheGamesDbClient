package apps.scvh.com.thegamesdbclient.frontend.utils;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewGetter {

    private Context context;

    public RecyclerViewGetter(Context context) {
        this.context = context;
    }

    public RecyclerView getRecyclerView() {
        RecyclerView view = new RecyclerView(context);
        view.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
                false));
        return view;
    }
}
