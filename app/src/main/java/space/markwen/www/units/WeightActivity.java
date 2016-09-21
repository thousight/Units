package space.markwen.www.units;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by markw on 9/20/2016.
 */

public class WeightActivity extends Fragment {

    View weightView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Change FrameLayout to content_weight
        weightView = inflater.inflate(R.layout.content_weight, container, false);
        // Change the title on titlebar to Weight
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Weight");
        return weightView;
    }

}
