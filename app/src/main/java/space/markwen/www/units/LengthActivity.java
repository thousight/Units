package space.markwen.www.units;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by markw on 9/20/2016.
 */

    public class LengthActivity extends Fragment {

        View lengthView;
        Spinner spinner;
        String[] lengthUnits = {
            "km", "m", "cm", "mm", "μm", "nm", "mi", "yd", "ft", "in"
        };
        EditText textbox;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Change FrameLayout to content_length
            lengthView = inflater.inflate(R.layout.content_length, container, false);

            // Textbox handler
            textbox = (EditText) lengthView.findViewById(R.id.lengthEditText);
            textbox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //km
                    TextView kmText = (TextView) lengthView.findViewById(R.id.kmText);
                    kmText.setText(s);

                    //m
                    TextView mText = (TextView) lengthView.findViewById(R.id.mText);
                    mText.setText(s);

                    //cm
                    TextView cmText = (TextView) lengthView.findViewById(R.id.cmText);
                    cmText.setText(s);

                    //mm
                    TextView mmText = (TextView) lengthView.findViewById(R.id.mmText);
                    mmText.setText(s);

                    //um
                    TextView umText = (TextView) lengthView.findViewById(R.id.umText);
                    umText.setText(s);

                    //nm
                    TextView nmText = (TextView) lengthView.findViewById(R.id.nmText);
                    nmText.setText(s);

                    //mi
                    TextView miText = (TextView) lengthView.findViewById(R.id.miText);
                    miText.setText(s);

                    //yd
                    TextView ydText = (TextView) lengthView.findViewById(R.id.ydText);
                    ydText.setText(s);

                    //ft
                    TextView ftText = (TextView) lengthView.findViewById(R.id.ftText);
                    ftText.setText(s);

                    //in
                    TextView inText = (TextView) lengthView.findViewById(R.id.inText);
                    inText.setText(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

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
