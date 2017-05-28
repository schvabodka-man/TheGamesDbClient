package apps.scvh.com.thegamesdbclient.frontend.list;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;

public class SameGamesAdapter extends RecyclerView.Adapter<SameGamesAdapter.ViewHolder> {

    private ArrayList<GameData> data;
    private Context context;

    public SameGamesAdapter(ArrayList<GameData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.same_game, parent,
                false);
        return new SameGamesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GameData gameData = data.get(position);
        Picasso.with(context).load(gameData.getCoverURL()).resize(200, 200).into(holder.cover);
        holder.clickInit(gameData.getId(), context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;

        ViewHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.same_game_cover);
        }

        void clickInit(int gameId, Context context) {
            cover.setOnClickListener(v -> {
                Intent intent = new Intent(context, Game.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(context.getString(R.string.bundle_id), gameId);
                context.startActivity(intent);
            });
        }
    }
}
