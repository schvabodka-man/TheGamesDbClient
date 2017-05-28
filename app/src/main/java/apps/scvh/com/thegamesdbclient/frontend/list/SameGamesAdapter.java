package apps.scvh.com.thegamesdbclient.frontend.list;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;

public class SameGamesAdapter extends RecyclerView.Adapter<SameGamesAdapter.ViewHolder> {

    private ArrayList<GameData> data;

    public SameGamesAdapter(ArrayList<GameData> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        ImageView cover;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView;
            cover = (ImageView) itemView.findViewById(R.id.same_game_cover);
            name = (TextView) itemView.findViewById(R.id.same_game_name);
        }
    }
}
