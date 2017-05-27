package apps.scvh.com.thegamesdbclient.frontend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.GameRetriever;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;
import apps.scvh.com.thegamesdbclient.dagger.Injector;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class Game extends AppCompatActivity {

    @Inject
    @Named("GameRetriever")
    GameRetriever retriever;

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

    private ShareActionProvider actionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Injector.inject(this);
        ButterKnife.bind(this);
        initActionBar(getSupportActionBar());
        populateUI(receiveGameFromIntent());
    }

    private Observable<GameData> receiveGameFromIntent() {
        return retriever.getGame(getIntent().getIntExtra(getString(R.string.bundle_id), 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        MenuItem searchButton = menu.findItem(R.id.menu_item_share);
        initShareProvider((ShareActionProvider) MenuItemCompat.getActionProvider(searchButton));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_in_game) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        return true;
    }

    private void populateUI(Observable<GameData> data) {
        data.subscribe(data1 -> {
            gameName.setText(data1.getName());
            if (data1.getCoverURL() != null) {
                Picasso.with(getBaseContext()).load(data1.getCoverURL()).resize(300, 200).into
                        (cover);
            }
            if (data1.getRating() != 0) {
                rating.setText(getString(R.string.rating, String.valueOf((int) data1.getRating())));
            }
            if (data1.getPopularity() != 0) {
                popularity.setText(getString(R.string.popularity, String.valueOf((int) data1
                        .getPopularity())));
            }
            if (data1.getReleaseTime() != null) {
                releaseDate.setText(getString(R.string.release_date, String.valueOf(data1
                                .getReleaseTime()
                                .getDayOfMonth()), String.valueOf(data1.getReleaseTime()
                                .getMonthOfYear()),
                        String.valueOf(data1.getReleaseTime().getYear())));
            }
            if (data1.getTimeToComplete() != null) {
                timeToBeat.setText(getString(R.string.completition_time, String.valueOf(data1
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
                genre.setText(StringUtils.join(data1.getGenres(), getString(R.string.splitter)));
            }
            if (data1.getGameEngines() != null) {
                gameEngine.setText(getString(R.string.running_on_engine, StringUtils.join(data1
                        .getGameEngines(), getString(R.string.splitter))));
            }
            setShareIntent(data1.getUrl());
        });
    }

    private void initActionBar(ActionBar bar) {
        bar.setShowHideAnimationEnabled(false);
        bar.setDisplayShowTitleEnabled(false);
        bar.setElevation(0);
        bar.setDisplayHomeAsUpEnabled(true);
    }

    private void initShareProvider(ShareActionProvider provider) {
        actionProvider = provider;
    }

    private void setShareIntent(String url) {
        if (actionProvider != null) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType(getString(R.string.mime_type_for_sharing));
            sharingIntent.putExtra(Intent.EXTRA_TEXT, url);
            actionProvider.setShareIntent(Intent.createChooser(sharingIntent, getString(R.string
                    .share_url)));
        }
    }
}
