package apps.scvh.com.thegamesdbclient.frontend.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private ArrayList<GameData> data;
    private Context context;

    public AdapterList(ArrayList<GameData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        if (data.get(position).getSummary() != null) {
            holder.summary.setText(data.get(position).getSummary());
        }
        holder.clickInit(data.get(position).getId(), context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        ImageView image;
        TextView name;
        TextView summary;

        ViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.game_card);
            image = (ImageView) itemView.findViewById(R.id.cover);
            name = (TextView) itemView.findViewById(R.id.game_name_list);
            summary = (TextView) itemView.findViewById(R.id.summary_list);
        }

        void clickInit(int gameId, Context context) {
            card.setOnClickListener(v -> {
                Intent intent = new Intent(context, Game.class);
                intent.putExtra(context.getString(R.string.bundle_id), gameId);
                context.startActivity(intent);
            });
        }
    }
}
