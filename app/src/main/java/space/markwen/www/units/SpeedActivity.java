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

import static space.markwen.www.units.R.id.cmText;
import static space.markwen.www.units.R.id.ftText;
import static space.markwen.www.units.R.id.inText;
import static space.markwen.www.units.R.id.kmText;
import static space.markwen.www.units.R.id.mText;
import static space.markwen.www.units.R.id.miText;
import static space.markwen.www.units.R.id.mmText;
import static space.markwen.www.units.R.id.nmText;
import static space.markwen.www.units.R.id.umText;
import static space.markwen.www.units.R.id.ydText;

/**
 * Created by markw on 9/20/2016.
 */

    public class SpeedActivity extends Fragment {

        View lengthView;
        Spinner spinner;
        String selectedUnit;
        double standard = 1.000000; // convert input into meters
        String[] lengthUnits = { "km", "m", "cm", "mm", "μm", "nm", "mi", "yd", "ft", "in" };
        EditText textbox;
        AppCompatActivity activity;
        LinearLayout colorBoard;

        TextView kmTextView;
        TextView mTextView;
        TextView cmTextView;
        TextView mmTextView;
        TextView umTextView;
        TextView nmTextView;
        TextView miTextView;
        TextView ydTextView;
        TextView ftTextView;
        TextView inTextView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            lengthView = inflater.inflate(R.layout.content_length, container, false); // Change FrameLayout to content_length
            activity = ((AppCompatActivity) getActivity());
            spinner = (Spinner) lengthView.findViewById(R.id.lengthSpinner);
            textbox = (EditText) lengthView.findViewById(R.id.lengthEditText);
            colorBoard = (LinearLayout) lengthView.findViewById(R.id.lengthColorBoard);

            // Changing theme
            activity.getSupportActionBar().setTitle("Length");
            activity.setTheme(R.style.LengthTheme); // Theme
            activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333"))); // Action bar
            colorBoard.setBackgroundColor(Color.parseColor("#333333")); // colorBoard
            textbox.getBackground().setColorFilter(Color.parseColor("#1EC7AC"), PorterDuff.Mode.SRC_IN); // EditText
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(Color.parseColor("#212121")); // Status bar
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

            return lengthView;
        }


        // Methods
        // Method that converts all the units and display them
        private void convertInput(double input) {
            kmTextView = (TextView) lengthView.findViewById(kmText);
            mTextView = (TextView) lengthView.findViewById(mText);
            cmTextView = (TextView) lengthView.findViewById(cmText);
            mmTextView = (TextView) lengthView.findViewById(mmText);
            umTextView = (TextView) lengthView.findViewById(umText);
            nmTextView = (TextView) lengthView.findViewById(nmText);
            miTextView = (TextView) lengthView.findViewById(miText);
            ydTextView = (TextView) lengthView.findViewById(ydText);
            ftTextView = (TextView) lengthView.findViewById(ftText);
            inTextView = (TextView) lengthView.findViewById(inText);

            if (input != 0) {
                // Set standard value in meters based on selected unit
                switch (selectedUnit) {
                    case "km":
                        standard = input / 0.001;
                        break;
                    case "m":
                        standard = input;
                        break;
                    case "cm":
                        standard = input / 100;
                        break;
                    case "mm":
                        standard = input / 1000;
                        break;
                    case "μm":
                        standard = input / 1000000;
                        break;
                    case "nm":
                        standard = input / 1000000000;
                        break;
                    case "mi":
                        standard = input / 0.000621371;
                        break;
                    case "yd":
                        standard = input / 1.09361;
                        break;
                    case "ft":
                        standard = input / 3.28084;
                        break;
                    case "in":
                        standard = input / 39.3701;
                        break;
                    default:
                        standard = input;
                        break;
                }
                //km
                kmTextView.setText(numberFormatter(standard * 0.001));
                //m
                mTextView.setText(numberFormatter(standard * 1));
                //cm
                cmTextView.setText(numberFormatter(standard * 100));
                //mm
                mmTextView.setText(numberFormatter(standard * 1000));
                //um
                umTextView.setText(numberFormatter(standard * 1000000));
                //nm
                nmTextView.setText(numberFormatter(standard * 1000000000));
                //mi
                miTextView.setText(numberFormatter(standard * 0.000621371));
                //yd
                ydTextView.setText(numberFormatter(standard * 1.09361));
                //ft
                ftTextView.setText(numberFormatter(standard * 3.28084));
                //in
                inTextView.setText(numberFormatter(standard * 39.3701));
            } else {
                //km
                kmTextView.setText("");
                //m
                mTextView.setText("");
                //cm
                cmTextView.setText("");
                //mm
                mmTextView.setText("");
                //um
                umTextView.setText("");
                //nm
                nmTextView.setText("");
                //mi
                miTextView.setText("");
                //yd
                ydTextView.setText("");
                //ft
                ftTextView.setText("");
                //in
                inTextView.setText("");
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
