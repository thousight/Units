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
import android.text.InputFilter;
import android.text.InputType;
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

import static space.markwen.www.units.R.id.binaryText;
import static space.markwen.www.units.R.id.decimalText;
import static space.markwen.www.units.R.id.hexText;

/**
 * Created by markw on 9/20/2016.
 */

    public class ProgrammingActivity extends Fragment {

        View programmingView;
        Spinner spinner;
        String selectedUnit;
        int standard = 0; // convert input into meters
        String[] programmingUnits = { "DEC", "BIN", "HEX" };
        EditText textbox;
        AppCompatActivity activity;
        LinearLayout colorBoard;

        TextView decimalTextView;
        TextView binaryTextView;
        TextView hexTextView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            programmingView = inflater.inflate(R.layout.content_programming, container, false); // Change FrameLayout to content_length
            activity = ((AppCompatActivity) getActivity());
            spinner = (Spinner) programmingView.findViewById(R.id.programmingSpinner);
            textbox = (EditText) programmingView.findViewById(R.id.programmingEditText);
            colorBoard = (LinearLayout) programmingView.findViewById(R.id.programmingColorBoard);

            decimalTextView = (TextView) programmingView.findViewById(decimalText);
            binaryTextView = (TextView) programmingView.findViewById(binaryText);
            hexTextView = (TextView) programmingView.findViewById(hexText);

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
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, programmingUnits);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    selectedUnit = programmingUnits[position];
                    if (position == 1) {
                        textbox.setInputType(InputType.TYPE_CLASS_NUMBER);
                        // Make text input all cap and limit to 16 digits
                        textbox.setFilters(new InputFilter[] {
                                new InputFilter.AllCaps(),
                                new InputFilter.LengthFilter(16)
                        });
                    }
                    else if (position == 2) {
                        textbox.setInputType(InputType.TYPE_CLASS_TEXT);
                        // Make text input all cap and limit to 16 digits
                        textbox.setFilters(new InputFilter[] {
                                new InputFilter.AllCaps(),
                                new InputFilter.LengthFilter(16)
                        });
                    } else {
                        textbox.setInputType(InputType.TYPE_CLASS_NUMBER);
                        // Make text input all cap and limit to 9 digits
                        textbox.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    }
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
        private void convertInput(String input) {

            if (input != "0") {
                // Set standard value in meters based on selected unit
                switch (selectedUnit) {
                    case "DEC":
                        try {
                            standard = Integer.parseInt(input);
                        } catch(Exception e) {
                            convertInput("0");
                            return;
                        }
                        break;
                    case "BIN":
                        if (input.matches("[10]+")) {
                            standard = Integer.valueOf(input, 2);
                        } else {
                            convertInput("0");
                            return;
                        }
                        break;
                    case "HEX":
                        try {
                            standard = Integer.valueOf(input, 16);
                        } catch(Exception e) {
                            convertInput("0");
                            return;
                        }
                        break;
                    default:
                        standard = Integer.parseInt(input);
                        break;
                }
                //decimal
                decimalTextView.setText(numberFormatter(String.valueOf(standard), "dec"));
                //binary
                binaryTextView.setText(numberFormatter(Integer.toBinaryString(standard), "bin"));
                //hex
                hexTextView.setText(numberFormatter(Integer.toHexString(standard), "hex"));
            } else {
                //decimal
                decimalTextView.setText("");
                //binary
                binaryTextView.setText("");
                //hex
                hexTextView.setText("");
            }
        }

        // Method that formats the converted numbers
        private String numberFormatter(String input, String type) {
            if (type == "dec") {
                NumberFormat commaFormatter = new DecimalFormat("###,###,###,###"); // Adding comma
                return commaFormatter.format(Integer.valueOf(input));
            }
            return input.replaceAll("....", "$0 ").toUpperCase();
        }

        // Method that determines numbers that displays based on user input
        private void outputBasedOnText(Editable s) {
            String stringValue = s.toString();
            // If the last char in string is "."
            if (stringValue.matches("[A-Fa-f0-9]+")) { // Check if stringValue is alphabetic
                convertInput(stringValue);
            } else {
                convertInput("0");
            }
        }
    }
