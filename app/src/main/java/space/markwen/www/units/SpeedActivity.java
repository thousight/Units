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

import static space.markwen.www.units.R.id.KnotText;
import static space.markwen.www.units.R.id.MphText;
import static space.markwen.www.units.R.id.ftpsText;
import static space.markwen.www.units.R.id.kmphText;
import static space.markwen.www.units.R.id.mpsText;

/**
 * Created by markw on 9/20/2016.
 */

    public class SpeedActivity extends Fragment {

        View speedView;
        Spinner spinner;
        String selectedUnit;
        double standard = 1.000000; // convert input into meters
        String[] lengthUnits = { "Mph", "Ftps", "mps", "kmph", "Knot" };
        EditText textbox;
        AppCompatActivity activity;
        LinearLayout colorBoard;

        TextView MphTextView;
        TextView FtpsTextView;
        TextView mpsTextView;
        TextView kmphTextView;
        TextView KnotTextView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            speedView = inflater.inflate(R.layout.content_speed, container, false); // Change FrameLayout to content_length
            activity = ((AppCompatActivity) getActivity());
            spinner = (Spinner) speedView.findViewById(R.id.speedSpinner);
            textbox = (EditText) speedView.findViewById(R.id.speedEditText);
            colorBoard = (LinearLayout) speedView.findViewById(R.id.speedColorBoard);

            MphTextView = (TextView) speedView.findViewById(MphText);
            FtpsTextView = (TextView) speedView.findViewById(ftpsText);
            mpsTextView = (TextView) speedView.findViewById(mpsText);
            kmphTextView = (TextView) speedView.findViewById(kmphText);
            KnotTextView = (TextView) speedView.findViewById(KnotText);

            // Changing theme
            activity.getSupportActionBar().setTitle("Speed");
            activity.setTheme(R.style.SpeedTheme); // Theme
            activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5"))); // Action bar
            colorBoard.setBackgroundColor(Color.parseColor("#33B5E5")); // colorBoard
            textbox.getBackground().setColorFilter(Color.parseColor("#9E9E9E"), PorterDuff.Mode.SRC_IN); // EditText
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(Color.parseColor("#0277BD")); // Status bar
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

            return speedView;
        }


        // Methods
        // Method that converts all the units and display them
        private void convertInput(double input) {

            if (input != 0) {
                // Set standard value in meters based on selected unit
                switch (selectedUnit) {
                    case "Mph":
                        standard = input / 0.681818;
                        break;
                    case "Ftps":
                        standard = input;
                        break;
                    case "mps":
                        standard = input / 0.3048;
                        break;
                    case "kmph":
                        standard = input / 1.09728;
                        break;
                    case "Knot":
                        standard = input / 0.592484;
                        break;
                    default:
                        standard = input;
                        break;
                }
                //Mph
                MphTextView.setText(numberFormatter(standard * 0.681818));
                //Ftps
                FtpsTextView.setText(numberFormatter(standard * 1));
                //mps
                mpsTextView.setText(numberFormatter(standard * 0.3048));
                //kmph
                kmphTextView.setText(numberFormatter(standard * 1.09728));
                //Knot
                KnotTextView.setText(numberFormatter(standard * 0.592484));
            } else {
                //Mph
                MphTextView.setText("");
                //Ftps
                FtpsTextView.setText("");
                //mps
                mpsTextView.setText("");
                //kmph
                kmphTextView.setText("");
                //Knot
                KnotTextView.setText("");
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
