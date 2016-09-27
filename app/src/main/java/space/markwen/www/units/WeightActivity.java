package space.markwen.www.units;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static space.markwen.www.units.R.id.gText;
import static space.markwen.www.units.R.id.ozText;
import static space.markwen.www.units.R.id.tText;
import static space.markwen.www.units.R.id.kgText;
import static space.markwen.www.units.R.id.stText;
import static space.markwen.www.units.R.id.mgText;
import static space.markwen.www.units.R.id.LTText;
import static space.markwen.www.units.R.id.ugText;
import static space.markwen.www.units.R.id.lbText;

/**
 * Created by markw on 9/20/2016.
 */

public class WeightActivity extends Fragment {

    View weightView;
    Spinner spinner;
    String selectedUnit;
    double standard = 1.000000; // convert input into kg
    String[] lengthUnits = { "t", "kg", "g", "mg", "μg", "LT", "st", "lb", "oz" };
    EditText textbox;
    AppCompatActivity activity;
    LinearLayout colorBoard;

    TextView tTextView;
    TextView kgTextView;
    TextView gTextView;
    TextView mgTextView;
    TextView ugTextView;
    TextView LTTextView;
    TextView stTextView;
    TextView lbTextView;
    TextView ozTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        weightView = inflater.inflate(R.layout.content_weight, container, false); // Change FrameLayout to content_weight
        activity = ((AppCompatActivity) getActivity());
        spinner = (Spinner) weightView.findViewById(R.id.weightSpinner);
        textbox = (EditText) weightView.findViewById(R.id.weightEditText);
        colorBoard = (LinearLayout) weightView.findViewById(R.id.weightColorBoard);

        // Changing theme
        activity.getSupportActionBar().setTitle("Weight");
        activity.setTheme(R.style.WeightTheme); // Theme
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5"))); // Action bar
        colorBoard.setBackgroundColor(Color.parseColor("#3F51B5")); // colorBoard
        textbox.getBackground().setColorFilter(Color.parseColor("#E91E63"), PorterDuff.Mode.SRC_IN); // EditText
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.parseColor("#283593")); // Status bar
        }

        // Textbox handler
        textbox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int aozer) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Values change as textbox input changes
                outputBasedOnText(s);
            }
        });

        // Spinner handler
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lengthUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selectedUnit = lengthUnits[position];
                // Make sure values refreshes when spinner value changes
                outputBasedOnText(textbox.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return weightView;
    }


    // Methods
    // Method that converts all the units and display them
    private void convertInput(double input) {
        tTextView = (TextView) weightView.findViewById(tText);
        kgTextView = (TextView) weightView.findViewById(kgText);
        gTextView = (TextView) weightView.findViewById(gText);
        mgTextView = (TextView) weightView.findViewById(mgText);
        ugTextView = (TextView) weightView.findViewById(ugText);
        LTTextView = (TextView) weightView.findViewById(LTText);
        stTextView = (TextView) weightView.findViewById(stText);
        lbTextView = (TextView) weightView.findViewById(lbText);
        ozTextView = (TextView) weightView.findViewById(ozText);

        if (input != 0) {
            // Set standard value in meters based on selected unit
            switch (selectedUnit) {
                case "t":
                    standard = input / 0.001;
                    break;
                case "kg":
                    standard = input;
                    break;
                case "g":
                    standard = input / 1000;
                    break;
                case "mg":
                    standard = input / 1000000;
                    break;
                case "μg":
                    standard = input / 1000000000;
                    break;
                case "LT":
                    standard = input / 0.000984207;
                    break;
                case "st":
                    standard = input / 0.157473;
                    break;
                case "lb":
                    standard = input / 2.20462;
                    break;
                case "oz":
                    standard = input / 35.274;
                    break;
                default:
                    standard = input;
                    break;
            }
            //t
            tTextView.setText(numberFormatter(standard * 0.001));
            //kg
            kgTextView.setText(numberFormatter(standard * 1));
            //g
            gTextView.setText(numberFormatter(standard * 1000));
            //mg
            mgTextView.setText(numberFormatter(standard * 1000000));
            //ug
            ugTextView.setText(numberFormatter(standard * 1000000000));
            //LT
            LTTextView.setText(numberFormatter(standard * 0.000984207));
            //st
            stTextView.setText(numberFormatter(standard * 0.157473));
            //lb
            lbTextView.setText(numberFormatter(standard * 2.20462));
            //oz
            ozTextView.setText(numberFormatter(standard * 35.274));
        } else {
            //t
            tTextView.setText("");
            //kg
            kgTextView.setText("");
            //g
            gTextView.setText("");
            //mg
            mgTextView.setText("");
            //ug
            ugTextView.setText("");
            //LT
            LTTextView.setText("");
            //st
            stTextView.setText("");
            //lb
            lbTextView.setText("");
            //oz
            ozTextView.setText("");
        }
    }

    // Method that formats the converted numbers
    private String numberFormatter(double input) {
        // Formatters
        NumberFormat sciFormatter = new DecimalFormat("0.#####E0"); // Scientific notation
        NumberFormat commaFormatter = new DecimalFormat("###,###,###,###.###############"); // Adding comma

        // If input is in scientific notation already
        if ((String.valueOf(input)).indexOf("E") > 0) {
            return sciFormatter.format(input); // make the scientific notation neater
        }

        // If input is not in scientific notation
        // Number of digits of integer and decimal of input
        String[] splitter = String.format("%f", input).split("\\.");
        int intDigits = splitter[0].length();   // Before Decimal Count
        int decDigits;// After  Decimal Count
        if (splitter[1].equals("000000")){
            decDigits = 0;
        } else {
            decDigits= splitter[1].length();
        }

        if ((intDigits + decDigits) > 9) {
            return sciFormatter.format(input);
        }
        return commaFormatter.format(input);
    }

    // Method that determines numbers that displays based on user input
    private void outputBasedOnText(Editable s) {
        String stringValue = s.toString();
        if (stringValue.length() > 1 && stringValue.substring(stringValue.length() - 1).equals(".")) { // If the last char in string is "."
            convertInput(Double.parseDouble(stringValue.substring(0, stringValue.length() - 1)));
        } else if (!stringValue.matches("[-+]?\\d*\\.?\\d+")) { // Check if stringValue is numeric
            convertInput(0);
        } else {
            convertInput(Double.parseDouble(stringValue));
        }
    }
}
