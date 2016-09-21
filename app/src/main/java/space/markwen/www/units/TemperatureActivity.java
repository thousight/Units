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

public class TemperatureActivity extends Fragment {

    View temperatureView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Change FrameLayout to content_temperature
        temperatureView = inflater.inflate(R.layout.content_temperature, container, false);
        // Change the title on titlebar to Temperature
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Temperature");
        return temperatureView;
    }

}
