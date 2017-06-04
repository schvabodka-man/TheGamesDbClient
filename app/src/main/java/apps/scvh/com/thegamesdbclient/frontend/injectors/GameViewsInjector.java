package apps.scvh.com.thegamesdbclient.frontend.injectors;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.frontend.list.SameGamesAdapter;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Developer;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Game;
import apps.scvh.com.thegamesdbclient.frontend.utils.RecyclerViewWorker;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Injector for injecting game data into views
 * I'm using ButterKnife to inject my views
 */
public class GameViewsInjector {

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
    LinearLayout sameGames;
    private Game game;
    private RecyclerViewWorker viewWorker;

    public GameViewsInjector(Game game) {
        this.game = game;
        ButterKnife.bind(this, game);
    }

    public void setViewWorker(RecyclerViewWorker viewWorker) {
        this.viewWorker = viewWorker;
        viewWorker.initRecycler(sameGames);

    }

    public void populateUI(Observable<GameData> data, ProgressDialog dialog) {
        data.subscribe(data1 -> {
            gameName.setText(data1.getName());
            populateCover(cover, data1.getCoverURL());
            if (data1.getRating() != 0) {
                rating.setText(game.getString(R.string.rating, String.valueOf((int) data1
                        .getRating())));
            }
            if (data1.getPopularity() != 0) {
                popularity.setText(game.getString(R.string.popularity, String.valueOf((int) data1
                        .getPopularity())));
            }
            if (data1.getReleaseTime() != null) {
                releaseDate.setText(game.getString(R.string.release_date, String.valueOf(data1
                                .getReleaseTime()
                                .getDayOfMonth()), String.valueOf(data1.getReleaseTime()
                                .getMonthOfYear()),
                        String.valueOf(data1.getReleaseTime().getYear())));
            }
            if (data1.getTimeToComplete() != null) {
                timeToBeat.setText(game.getString(R.string.completition_time, String.valueOf
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
                genre.setText(StringUtils.join(data1.getGenres(), game.getString(R.string
                        .splitter)));
            }
            if (data1.getGameEngines() != null) {
                gameEngine.setText(game.getString(R.string.running_on_engine, StringUtils.join
                        (data1.getGameEngines(), game.getString(R.string.splitter))));
            }
            if (data1.getThemes() != null) {
                theme.setText(StringUtils.join(data1.getThemes(), game.getString(R.string
                        .splitter)));
            }
            if (data1.getGameModes() != null) {
                gamemodes.setText(StringUtils.join(data1.getGameModes(), game.getString(R.string
                        .splitter)));
            }
            if (data1.getPerspective() != null) {
                perspectives.setText(StringUtils.join(data1.getPerspective(), game.getString(R
                        .string
                        .splitter)));
            }
            populateSameGames(data1);
            if (data1.getDeveloper() != null) {
                devName.setText(data1.getDeveloper().getName());
                initDeveloperClickListener(data1.getDeveloper().getId());
            }
            dialog.dismiss();
        });
    }

    private void populateSameGames(GameData data) {
        if (data.getGameData() != null) {
            viewWorker.showRecycler(new SameGamesAdapter(data.getGameData(), game),
                    LinearLayoutManager.HORIZONTAL);
        }
    }

    private void populateCover(ImageView view, String coverURL) {
        if (coverURL != null) {
            Picasso.with(game).load(coverURL).resize(200, 200).into
                    (view);
        }
    }

    private void initDeveloperClickListener(int id) {
        devName.setOnClickListener(v -> {
            Intent intent = new Intent(game, Developer.class);
            intent.putExtra(game.getString(R.string.game_dev_key), id);
            game.startActivity(intent);
        });
    }
}
