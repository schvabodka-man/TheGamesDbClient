package apps.scvh.com.thegamesdbclient.frontend.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Named;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.GameRetriever;
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
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.time_to_beat)
    TextView timeToBeat;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.card_summary)
    CardView cardSummary;
    @BindView(R.id.story)
    TextView story;
    @BindView(R.id.card_story)
    CardView cardStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Injector.inject(this);
        ButterKnife.bind(this);
        populateUI(receiveGameFromIntent());
    }

    private Observable<GameData> receiveGameFromIntent() {
        return retriever.getGame(getIntent().getIntExtra(getString(R.string.bundle_id), 1));
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
        });
    }
}
