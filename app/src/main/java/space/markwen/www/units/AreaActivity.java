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

import static space.markwen.www.units.R.id.cm2Text;
import static space.markwen.www.units.R.id.ft2Text;
import static space.markwen.www.units.R.id.in2Text;
import static space.markwen.www.units.R.id.km2Text;
import static space.markwen.www.units.R.id.m2Text;
import static space.markwen.www.units.R.id.Mi2Text;
import static space.markwen.www.units.R.id.haText;
import static space.markwen.www.units.R.id.acText;
import static space.markwen.www.units.R.id.yd2Text;

/**
 * Created by markw on 9/27/2016.
 */

    public class AreaActivity extends Fragment {

        View areaView;
        Spinner spinner;
        String selectedUnit;
        double standard = 1.000000; // convert input into meters
        String[] lengthUnits = { "km^2", "m^2", "cm^2", "Mi^2", "yd^2", "ft^2", "in^2", "ha", "ac" };
        EditText textbox;
        AppCompatActivity activity;
        LinearLayout colorBoard;

        TextView km2TextView;
        TextView m2TextView;
        TextView cm2TextView;
        TextView Mi2TextView;
        TextView yd2TextView;
        TextView ft2TextView;
        TextView in2TextView;
        TextView haTextView;
        TextView acTextView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            areaView = inflater.inflate(R.layout.content_area, container, false); // Change FrameLayout to content_length
            activity = ((AppCompatActivity) getActivity());
            spinner = (Spinner) areaView.findViewById(R.id.areaSpinner);
            textbox = (EditText) areaView.findViewById(R.id.areaEditText);
            colorBoard = (LinearLayout) areaView.findViewById(R.id.areaColorBoard);

            // Changing theme
            activity.getSupportActionBar().setTitle("Area");
            activity.setTheme(R.style.AreaTheme); // Theme
            activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9c27b0"))); // Action bar
            colorBoard.setBackgroundColor(Color.parseColor("#9c27b0")); // colorBoard
            textbox.getBackground().setColorFilter(Color.parseColor("#4caf50"), PorterDuff.Mode.SRC_IN); // EditText
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(Color.parseColor("#7b1fa2")); // Status bar
            }

            // Textbox handler
            textbox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

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

            return areaView;
        }


        // Methods
        // Method that converts all the units and display them
        private void convertInput(double input) {
            km2TextView = (TextView) areaView.findViewById(km2Text);
            m2TextView = (TextView) areaView.findViewById(m2Text);
            cm2TextView = (TextView) areaView.findViewById(cm2Text);
            Mi2TextView = (TextView) areaView.findViewById(Mi2Text);
            yd2TextView = (TextView) areaView.findViewById(yd2Text);
            ft2TextView = (TextView) areaView.findViewById(ft2Text);
            in2TextView = (TextView) areaView.findViewById(in2Text);
            haTextView = (TextView) areaView.findViewById(haText);
            acTextView = (TextView) areaView.findViewById(acText);

            if (input != 0) {
                // Set standard value in meters based on selected unit
                switch (selectedUnit) {
                    case "km^2":
                        standard = input / 0.000001;
                        break;
                    case "m^2":
                        standard = input;
                        break;
                    case "cm^2":
                        standard = input / 10000;
                        break;
                    case "Mi^2":
                        standard = input / 0.0000003861;
                        break;
                    case "yd^2":
                        standard = input / 1.19599;
                        break;
                    case "ft^2":
                        standard = input / 10.7639;
                        break;
                    case "in^2":
                        standard = input / 1550;
                        break;
                    case "ha":
                        standard = input / 0.0001;
                        break;
                    case "ac":
                        standard = input / 0.000247105;
                        break;
                    default:
                        standard = input;
                        break;
                }
                //km2
                km2TextView.setText(numberFormatter(standard * 0.000001));
                //m2
                m2TextView.setText(numberFormatter(standard * 1));
                //cm2
                cm2TextView.setText(numberFormatter(standard * 10000));
                //Mi2
                Mi2TextView.setText(numberFormatter(standard * 0.0000003861));
                //yd2
                yd2TextView.setText(numberFormatter(standard * 1.19599));
                //ft2
                ft2TextView.setText(numberFormatter(standard * 10.7639));
                //in2
                in2TextView.setText(numberFormatter(standard * 1550));
                //ha
                haTextView.setText(numberFormatter(standard * 0.0001));
                //ac
                acTextView.setText(numberFormatter(standard * 0.000247105));
            } else {
                //km2
                km2TextView.setText("");
                //m2
                m2TextView.setText("");
                //cm2
                cm2TextView.setText("");
                //Mi2
                Mi2TextView.setText("");
                //yd2
                yd2TextView.setText("");
                //ft2
                ft2TextView.setText("");
                //in2
                in2TextView.setText("");
                //ha
                haTextView.setText("");
                //ac
                acTextView.setText("");
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
