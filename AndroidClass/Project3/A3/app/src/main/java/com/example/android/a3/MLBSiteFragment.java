package com.example.android.a3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by zhang4all on 3/26/2017.
 */

public class MLBSiteFragment extends Fragment {
    private static final String TAG = "SitesFragment";
    private WebView mSiteView = null;
    private int mCurrIdx = -1;
    private int mQuoteArrLen;
    public static String[] mSiteArray;


    public void showSiteAtIndex(int idx) {

        mSiteView.loadUrl(mSiteArray[idx]);
    }


    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        mSiteArray = getResources().getStringArray(R.array.mlbSites);

        View view = inflater.inflate(R.layout.site_fragment, container, false);

        mSiteView = (WebView) view.findViewById(R.id.siteView);

        // mSiteView.loadUrl(mSiteArray[newIndex]);

        // Inflate the layout defined in quote_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return view;
    }


    // Set up some information about the mQuoteView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        mSiteView = (WebView) getActivity().findViewById(R.id.siteView);
    }


    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }


    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }


    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }


    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }


    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }


    /**
     * this method inflates the options menu from the xml file
     * @param menu
     * @return a boolean indicating whether the menu is displayed
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.options_menu, menu);
    }


    /**
     * implements the action based on the item selected in the menu
     * @param item this is the menu item that was selected
     * @return false to allow normal menu processing to proceed, true means it is done processing this menu selection
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mlb) {
            Intent nbaIntent = new Intent();
            nbaIntent.setClass(getActivity(), NBAActivity.class);
            startActivity(nbaIntent);
            return true;
        }
        return false;
    }
}
