package apps.scvh.com.thegamesdbclient.frontend;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.frontend.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.list.SameGamesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class GameViewsInjector {

    private Context context;

    @BindView(R.id.game_image)
    ImageView cover;
    @BindView(R.id.game_name)
    TextView gameName;
    @BindView(R.id.game_genre)
    TextView genre;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.time_to_beat)
    TextView timeToBeat;
    @BindView(R.id.game_engine)
    TextView gameEngine;
    @BindView(R.id.theme)
    TextView theme;
    @BindView(R.id.gamemodes)
    TextView gamemodes;
    @BindView(R.id.perspectives)
    TextView perspectives;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.card_summary)
    CardView cardSummary;
    @BindView(R.id.story)
    TextView story;
    @BindView(R.id.card_story)
    CardView cardStory;
    @BindView(R.id.card_esrb)
    CardView esrbCard;
    @BindView(R.id.card_pegi)
    CardView pegiCard;
    @BindView(R.id.esrb)
    TextView esrb;
    @BindView(R.id.pegi)
    TextView pegi;
    @BindView(R.id.dev_game_name)
    TextView devName;
    @BindView(R.id.same_games)
    RecyclerView sameGames;

    public GameViewsInjector(Game game) {
        context = game.getBaseContext();
        ButterKnife.bind(this, game);
    }

    public void populateUI(Observable<GameData> data, ProgressDialog dialog) {
        data.subscribe(data1 -> {
            gameName.setText(data1.getName());
            populateCover(cover, data1.getCoverURL());
            if (data1.getRating() != 0) {
                rating.setText(context.getString(R.string.rating, String.valueOf((int) data1
                        .getRating())));
            }
            if (data1.getPopularity() != 0) {
                popularity.setText(context.getString(R.string.popularity, String.valueOf((int) data1
                        .getPopularity())));
            }
            if (data1.getReleaseTime() != null) {
                releaseDate.setText(context.getString(R.string.release_date, String.valueOf(data1
                                .getReleaseTime()
                                .getDayOfMonth()), String.valueOf(data1.getReleaseTime()
                                .getMonthOfYear()),
                        String.valueOf(data1.getReleaseTime().getYear())));
            }
            if (data1.getTimeToComplete() != null) {
                timeToBeat.setText(context.getString(R.string.completition_time, String.valueOf
                        (data1
                                .getTimeToComplete().getMillis() / 3600000)));
            }
            if (data1.getSummary() != null) {
                cardSummary.setVisibility(View.VISIBLE);
                summary.setText(data1.getSummary());
            }
            if (data1.getStoryline() != null) {
                cardStory.setVisibility(View.VISIBLE);
                story.setText(data1.getStoryline());
            }
            if (data1.getPegi() != null) {
                pegiCard.setVisibility(View.VISIBLE);
                pegi.setText(data1.getPegi());
            }
            if (data1.getEsrb() != null) {
                esrbCard.setVisibility(View.VISIBLE);
                esrb.setText(data1.getEsrb());
            }
            if (data1.getGenres() != null) {
                genre.setText(StringUtils.join(data1.getGenres(), context.getString(R.string
                        .splitter)));
            }
            if (data1.getGameEngines() != null) {
                gameEngine.setText(context.getString(R.string.running_on_engine, StringUtils.join
                        (data1.getGameEngines(), context.getString(R.string.splitter))));
            }
            if (data1.getThemes() != null) {
                theme.setText(StringUtils.join(data1.getThemes(), context.getString(R.string
                        .splitter)));
            }
            if (data1.getGameModes() != null) {
                gamemodes.setText(StringUtils.join(data1.getGameModes(), context.getString(R.string
                        .splitter)));
            }
            if (data1.getPerspective() != null) {
                perspectives.setText(StringUtils.join(data1.getPerspective(), context.getString(R
                        .string
                        .splitter)));
            }
            if (data1.getGameData() != null) {
                sameGames.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager
                        .HORIZONTAL, false));
                sameGames.setAdapter(new SameGamesAdapter(data1.getGameData(), context));
            }
            if (data1.getDeveloper() != null) {
                devName.setText(data1.getDeveloper().getName());
            }
            dialog.dismiss();
        });
    }

    private void populateCover(ImageView view, String coverURL) {
        if (coverURL != null) {
            Picasso.with(context).load(coverURL).resize(200, 200).into
                    (view);
        }
    }

}
