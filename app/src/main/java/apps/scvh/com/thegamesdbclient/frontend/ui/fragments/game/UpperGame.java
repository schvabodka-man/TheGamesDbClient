package apps.scvh.com.thegamesdbclient.frontend.ui.fragments.game;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.scvh.com.thegamesdbclient.R;


public class UpperGame extends Fragment {


    public UpperGame() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upper_game, container, false);
    }

}
