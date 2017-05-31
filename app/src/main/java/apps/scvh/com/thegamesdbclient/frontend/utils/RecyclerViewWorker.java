package apps.scvh.com.thegamesdbclient.frontend.utils;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class RecyclerViewWorker {

    private Context context;
    private RecyclerView holder;

    public RecyclerViewWorker(Context context) {
        this.context = context;
    }

    public void initRecycler(LinearLayout layout) {
        holder = new RecyclerView(context);
        holder.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false));
        layout.addView(holder);
    }

    public void showRecycler(RecyclerView.Adapter adapter, int orientation) {
        holder.setLayoutManager(new LinearLayoutManager(context, orientation,
                false));
        holder.setAdapter(adapter);
    }

}
