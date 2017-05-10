package com.example.android.project1;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView outputTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputTextView = (TextView) findViewById(R.id.outputText);
    }


    /**
     * When the topButton is clicked, Activity1 is loaded
     * @param view
     */
    public void topButton(View view) {
        // Create a new intent to open the {@link NumbersActivity}
        Intent i = new Intent(MainActivity.this, Activity1.class);

        // Start the new activity
        startActivity(i);
    }


    /**
     * when the bottomButton is clicked, a browser opens up to the page "https://developer.android.com/index.html"
     * @param view
     */
    public void bottomButton(View view) {
        // Create a new intent to open the {@link NumbersActivity}
        Uri webpage = Uri.parse("https://developer.android.com/index.html");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
