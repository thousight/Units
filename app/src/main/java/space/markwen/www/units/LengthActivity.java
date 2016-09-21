package space.markwen.www.units;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by markw on 9/20/2016.
 */

    public class LengthActivity extends Fragment {

        View lengthView;
        Spinner spinner;
        String[] lengthUnits = {
            "km", "m", "cm", "mm", "μm", "nm", "mi", "yd", "ft", "in"
        };

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Change FrameLayout to content_length
            lengthView = inflater.inflate(R.layout.content_length, container, false);
            // Spinner handler
            spinner = (Spinner) lengthView.findViewById(R.id.lengthSpinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<String> adapter = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_spinner_item, lengthUnits);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            // Change the title on titlebar to Length
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Length");

            return lengthView;
        }



    }
