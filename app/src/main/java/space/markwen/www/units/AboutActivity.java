package space.markwen.www.units;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by markw on 9/20/2016.
 */

public class AboutActivity extends Fragment {

    View aboutView;
    AppCompatActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        aboutView = inflater.inflate(R.layout.content_about, container, false); // Change FrameLayout to content_about
        activity = (AppCompatActivity)getActivity();
        activity.setTheme(R.style.AppTheme); // Theme
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333"))); // Action bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.parseColor("#212121")); // Status bar
        }
        // Change the title on titlebar to About
        activity.getSupportActionBar().setTitle("About");


        return aboutView;
    }

}
