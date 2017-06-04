package apps.scvh.com.thegamesdbclient.frontend.injectors;


import android.app.ProgressDialog;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.models.GameDeveloper;
import apps.scvh.com.thegamesdbclient.frontend.ui.activities.Developer;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class DeveloperInjector {

    private Developer developer;

    @BindView(R.id.developer_image)
    ImageView image;
    @BindView(R.id.developer_name)
    TextView name;
    @BindView(R.id.developer_summary)
    TextView summary;
    @BindView(R.id.developer_date)
    TextView date;
    @BindView(R.id.developer_site)
    TextView site;

    public DeveloperInjector(Developer developer) {
        this.developer = developer;
        ButterKnife.bind(this, developer);
    }

    public void populateUI(Observable<GameDeveloper> developerObservable, ProgressDialog dialog) {
        developerObservable.subscribe(developer1 -> {
            if (developer1.getCoverLink() != null) {
                Picasso.with(developer).load(developer1.getCoverLink().replace(developer
                        .getString(R.string
                                .to_replace), developer.getString(R.string.repalce_with))).resize
                        (200,
                                200).into
                        (image);
            }
            if (developer1.getName() != null) {
                name.setText(developer1.getName());
            }
            if (developer1.getDescription() != null) {
                summary.setText(developer1.getDescription());
            }
            if (developer1.getWebsite() != null) {
                site.setText(developer1.getWebsite());
            }
            if (developer1.getDate() != null) {
                date.setText(developer.getString(R.string.foundation_date, String.valueOf(developer1
                        .getDate().getDayOfMonth()), String.valueOf(developer1.getDate()
                        .getMonthOfYear()), String.valueOf(developer1.getDate().getYear())));
            }
            dialog.dismiss();
        });
    }

}
