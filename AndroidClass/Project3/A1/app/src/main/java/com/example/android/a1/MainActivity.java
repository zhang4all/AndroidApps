package com.example.android.a1;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;

import static android.content.Intent.FLAG_INCLUDE_STOPPED_PACKAGES;


public class MainActivity extends Activity {
    private static final String PROJECT3_PERMISSION = "edu.uic.cs478.project3";
    private static final String CUSTOM_INTENT_NBA = "com.example.android.a1nba";
    private static final String CUSTOM_INTENT_MLB = "com.example.android.a1mlb";
    private static final int PERMISSION_CODE_NBA = 1;
    private static final int PERMISSION_CODE_MLB = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * checks for permission
     * if permission granted, send the broadcast
     * if permission denied, request permission
     * @param view
     */
    public void nbaButton(View view) {
        // check permission
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, PROJECT3_PERMISSION);
        if (PackageManager.PERMISSION_GRANTED == permissionCheck) {
            Intent i = new Intent(CUSTOM_INTENT_NBA);
            i.addFlags(FLAG_INCLUDE_STOPPED_PACKAGES);
            sendOrderedBroadcast(i, null);
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"edu.uic.cs478.project3"}, PERMISSION_CODE_NBA);
        }
    }


    /**
     * checks for permission
     * if permission granted, send the broadcast
     * if permission denied, request permission
     * @param view
     */
    public void mlbButton(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, "edu.uic.cs478.project3");
        if (PackageManager.PERMISSION_GRANTED == permissionCheck) {
            Intent i = new Intent(CUSTOM_INTENT_MLB);
            i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendOrderedBroadcast(i, null);
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"edu.uic.cs478.project3"}, PERMISSION_CODE_MLB);
        }
    }


    /**
     * After response to permission pop up is sent, this will send broadcast if permission is granted
     * @param requestCode code for nba or mlb
     * @param permissions the permissions responded to
     * @param grantResults is an array of permissions granted or denied
     */
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE_NBA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(CUSTOM_INTENT_NBA);
                    i.addFlags(FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendOrderedBroadcast(i, null);
                }
            }
            case PERMISSION_CODE_MLB: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(CUSTOM_INTENT_MLB);
                    i.addFlags(FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendOrderedBroadcast(i, "edu.uic.cs478.project3");
                }
            }
        }
    }
}
