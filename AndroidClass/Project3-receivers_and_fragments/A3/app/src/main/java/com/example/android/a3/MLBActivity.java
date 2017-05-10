package com.example.android.a3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


/**
 * Created by zhang4all on 3/25/2017.
 */

public class MLBActivity extends AppCompatActivity implements MLBNameFragment.ListSelectionListener {
    private static final String PROJECT3_PERMISSION = "edu.uic.cs478.project3";
    private static final int PERMISSION_CODE_MLB = 1;
    private int idx;
    public static String[] mTeamArray;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTeamArray = getResources().getStringArray(R.array.mlbTeams);

        setContentView(R.layout.activity_mlb);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mlb_name_fragment_container, new MLBNameFragment());
        fragmentTransaction.commit();
    }


    /**
     * called when user selects a mlb team name
     * this creates and displays the fragment for mlb teams
     * @param index is the number of the team selected
     */
    @Override
    public void onListSelection(int index) {
        idx = index;
        int permissionCheck = ContextCompat.checkSelfPermission(MLBActivity.this, PROJECT3_PERMISSION);
        if (PackageManager.PERMISSION_GRANTED == permissionCheck) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MLBSiteFragment mlbSite = new MLBSiteFragment();
            fragmentTransaction.replace(R.id.mlb_name_fragment_container, mlbSite);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
            mlbSite.showSiteAtIndex(idx);
        }
        else {
            ActivityCompat.requestPermissions(MLBActivity.this, new String[]{"edu.uic.cs478.project3"}, PERMISSION_CODE_MLB);
        }
    }


    /**
     * After response to permission pop up is sent, this will create and displays the fragment for mlb teams
     * @param requestCode code for mlb
     * @param permissions permissions the permissions responded to
     * @param grantResults grantResults is an array of permissions granted or denied
     */
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE_MLB: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    MLBSiteFragment mlbSite = new MLBSiteFragment();
                    fragmentTransaction.replace(R.id.mlb_name_fragment_container, mlbSite);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    fragmentManager.executePendingTransactions();
                    mlbSite.showSiteAtIndex(idx);
                }
            }
        }
    }


    /**
     * this method inflates the options menu from the xml file
     * @param menu
     * @return a boolean indicating whether the menu is displayed
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    /**
     * implements the action based on the item selected in the menu
     * @param item this is the menu item that was selected
     * @return false to allow normal menu processing to proceed, true means it is done processing this menu selection
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nba) {
            Intent nbaIntent = new Intent();
            nbaIntent.setClass(MLBActivity.this, NBAActivity.class);
            startActivity(nbaIntent);
            return true;
        }
        return false;
    }
}
