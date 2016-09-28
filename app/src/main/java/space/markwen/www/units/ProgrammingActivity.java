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

    public class ProgrammingActivity extends Fragment {

        View programmingView;
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

            programmingView = inflater.inflate(R.layout.content_programming, container, false); // Change FrameLayout to content_length
            activity = ((AppCompatActivity) getActivity());
            spinner = (Spinner) programmingView.findViewById(R.id.programmingSpinner);
            textbox = (EditText) programmingView.findViewById(R.id.programmingEditText);
            colorBoard = (LinearLayout) programmingView.findViewById(R.id.programmingColorBoard);

            // Changing theme
            activity.getSupportActionBar().setTitle("Programming");
            activity.setTheme(R.style.ProgrammingTheme); // Theme
            activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6D4C41"))); // Action bar
            colorBoard.setBackgroundColor(Color.parseColor("#6D4C41")); // colorBoard
            textbox.getBackground().setColorFilter(Color.parseColor("#018989"), PorterDuff.Mode.SRC_IN); // EditText
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(Color.parseColor("#3E2723")); // Status bar
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

            return programmingView;
        }


        // Methods
        // Method that converts all the units and display them
        private void convertInput(double input) {
            kmTextView = (TextView) programmingView.findViewById(kmText);
            mTextView = (TextView) programmingView.findViewById(mText);
            cmTextView = (TextView) programmingView.findViewById(cmText);
            mmTextView = (TextView) programmingView.findViewById(mmText);
            umTextView = (TextView) programmingView.findViewById(umText);
            nmTextView = (TextView) programmingView.findViewById(nmText);
            miTextView = (TextView) programmingView.findViewById(miText);
            ydTextView = (TextView) programmingView.findViewById(ydText);
            ftTextView = (TextView) programmingView.findViewById(ftText);
            inTextView = (TextView) programmingView.findViewById(inText);

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
