package apps.scvh.com.thegamesdbclient.frontend.utils;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class RecyclerViewWorker {

    private Context context;
    private LinearLayout layout;
    private RecyclerView holder;

    public RecyclerViewWorker(Context context) {
        this.context = context;
    }

    private RecyclerView getRecyclerView() {
        RecyclerView view = new RecyclerView(context);
        view.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
                false));
        return view;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }

    public void initRecycler(RecyclerView.Adapter adapter) {
        holder = getRecyclerView();
        holder.setAdapter(adapter);
        layout.addView(holder);
    }

    public void removeRecycler() {
        layout.removeView(holder);
        holder = null;
    }

}
