package com.example.android.project1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Intent.ACTION_DIAL;

/**
 * Created by zhang4all on 1/31/2017.
 */


public class Activity1 extends AppCompatActivity {
    String inputNumber;
    String outputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
    }


    /**
     * When this button is clicked, it will check if the number entered is valid, if it is,
     * the MainActivity will display "Valid number", if it is not, the MainActivity will display "Not a valid number"
     * If the number is valid, the phone app will open with the number inputted
     * @param view
     */
    public void submitPhoneNumber(View view) {
        EditText phoneNumberEditText = (EditText) findViewById(R.id.phoneNumber);
        inputNumber = phoneNumberEditText.getText().toString();

        if (inputNumber.length()==0) {
            MainActivity.outputTextView.setText("Not valid number");
            return;
        }

        // check if matches
        if (!isValidNumber()) {
            MainActivity.outputTextView.setText("Not valid number");
        }
        else {
            // format the phone number
            MainActivity.outputTextView.setText("Valid number");
            Intent callIntent = new Intent(ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + outputNumber));
            if (callIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(callIntent);
            }
        }
    }


    /**
     * Takes in a string and checks to make sure it matches one of the three regular expressions:
     * yyy-zzzz, (xxx) yyy-zzzz, (xxx)yyy-zzzz
     * @return
     */
    public boolean isValidNumber() {
        // initialize an arraylist of classes for each pattern
        RegexPattern[] regexPatterns = new RegexPattern[3];
        for (int i=0; i<3; i++) {
            regexPatterns[i] = new RegexPattern(i+1);
        }

        //yyy-zzzz
        String REGEX0 = "\\d{3}-\\d{4}";
        populateRegexPattern(regexPatterns[0], REGEX0);

        //(xxx) yyy-zzzz
        String REGEX1 = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
        populateRegexPattern(regexPatterns[1], REGEX1);

        //(xxx)yyy-zzzz
        String REGEX2 = "\\(\\d{3}\\)\\d{3}-\\d{4}";
        populateRegexPattern(regexPatterns[2], REGEX2);

        if (!(regexPatterns[0].isPatternFound()) && !(regexPatterns[1].isPatternFound()) && !(regexPatterns[2].isPatternFound())) {
            return false;
        }

        setOutputNumber(regexPatterns);

        return true;
    }


    /**
     * if the input string matches the pattern, the RegexPattern class is filled out
     * @param the RegexPattern class
     * @param regex the regular expression passed in
     */
    void populateRegexPattern(RegexPattern pattern, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(inputNumber);
        if (m.find()) {
            pattern.setPatternFound(true);
            pattern.setFirstValidNumber(m.group(0));
            pattern.setPatternStartIdx(m.start());

            Log.i("firstValidNum", pattern.getFirstValidNumber());
            Log.i("startIdx", Integer.toString(pattern.getPatternStartIdx()));
        }
    }


    /**
     * loops through the array of RegexPatterns to find the valid pattern that starts the earliest
     * It updates the outputNumber member variable
     * @param regexPatterns
     */
    public void setOutputNumber(RegexPattern[] regexPatterns) {
        int firstIdx= Integer.MAX_VALUE;
        for (int i=0; i<3; i++) {
            if (regexPatterns[i].isPatternFound()) {
                if (regexPatterns[i].getPatternStartIdx() < firstIdx) {
                    firstIdx = regexPatterns[i].getPatternStartIdx();
                    outputNumber = regexPatterns[i].getFirstValidNumber();
                }
            }
        }
    }
}
