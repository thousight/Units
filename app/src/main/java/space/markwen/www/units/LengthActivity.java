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

    public class LengthActivity extends Fragment {

        View lengthView;
        Spinner spinner;
        String selectedUnit = "m";
        double standard = 1.000000; // convert input into meters
        String[] lengthUnits = { "km", "m", "cm", "mm", "μm", "nm", "mi", "yd", "ft", "in" };
        EditText textbox;
        String textboxText = null;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            // Change FrameLayout to content_length
            lengthView = inflater.inflate(R.layout.content_length, container, false);
            // Change the title on titlebar to Length
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Length");

            // Textbox handler
            textbox = (EditText) lengthView.findViewById(R.id.lengthEditText);
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
            spinner = (Spinner) lengthView.findViewById(R.id.lengthSpinner);
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

        // Method that converts all the units and display them
        private void convertInput(double input) {
            if (input != 0) {
                // Set standard value in meters based on selected unit
                switch (selectedUnit) {
                    case "km":
                        standard = input / 0.001;
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
                TextView kmTextView = (TextView) lengthView.findViewById(kmText);
                kmTextView.setText(numberFormatter(standard * 0.001));
                //m
                TextView mTextView = (TextView) lengthView.findViewById(mText);
                mTextView.setText(numberFormatter(standard * 1));
                //cm
                TextView cmTextView = (TextView) lengthView.findViewById(cmText);
                cmTextView.setText(numberFormatter(standard * 100));
                //mm
                TextView mmTextView = (TextView) lengthView.findViewById(mmText);
                mmTextView.setText(numberFormatter(standard * 1000));
                //um
                TextView umTextView = (TextView) lengthView.findViewById(umText);
                umTextView.setText(numberFormatter(standard * 1000000));
                //nm
                TextView nmTextView = (TextView) lengthView.findViewById(nmText);
                nmTextView.setText(numberFormatter(standard * 1000000000));
                //mi
                TextView miTextView = (TextView) lengthView.findViewById(miText);
                miTextView.setText(numberFormatter(standard * 0.000621371));
                //yd
                TextView ydTextView = (TextView) lengthView.findViewById(ydText);
                ydTextView.setText(numberFormatter(standard * 1.09361));
                //ft
                TextView ftTextView = (TextView) lengthView.findViewById(ftText);
                ftTextView.setText(numberFormatter(standard * 3.28084));
                //in
                TextView inTextView = (TextView) lengthView.findViewById(inText);
                inTextView.setText(numberFormatter(standard * 39.3701));
            } else {
                //km
                TextView kmTextView = (TextView) lengthView.findViewById(kmText);
                kmTextView.setText("");
                //m
                TextView mTextView = (TextView) lengthView.findViewById(mText);
                mTextView.setText("");
                //cm
                TextView cmTextView = (TextView) lengthView.findViewById(cmText);
                cmTextView.setText("");
                //mm
                TextView mmTextView = (TextView) lengthView.findViewById(mmText);
                mmTextView.setText("");
                //um
                TextView umTextView = (TextView) lengthView.findViewById(umText);
                umTextView.setText("");
                //nm
                TextView nmTextView = (TextView) lengthView.findViewById(nmText);
                nmTextView.setText("");
                //mi
                TextView miTextView = (TextView) lengthView.findViewById(miText);
                miTextView.setText("");
                //yd
                TextView ydTextView = (TextView) lengthView.findViewById(ydText);
                ydTextView.setText("");
                //ft
                TextView ftTextView = (TextView) lengthView.findViewById(ftText);
                ftTextView.setText("");
                //in
                TextView inTextView = (TextView) lengthView.findViewById(inText);
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
                String a = sciFormatter.format(input);
                return sciFormatter.format(input);
            }

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
