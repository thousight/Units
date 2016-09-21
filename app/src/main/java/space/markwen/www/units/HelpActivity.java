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

public class HelpActivity extends Fragment {

    View helpView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        helpView = inflater.inflate(R.layout.content_help, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Help");
        return helpView;
    }

}
