package com.example.android.a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zhang4all on 3/25/2017.
 */


public class A2Receiver extends BroadcastReceiver {


    /**
     * receives the broadcast for nba and mlb and responds accordingly
     * @param context
     * @param intent is the intent sent by the broadcaster
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction() ) {
            case "com.example.android.a1nba":
                Toast.makeText(context, "A2 nba ", Toast.LENGTH_LONG).show();
                break;
            case "com.example.android.a1mlb":
                Toast.makeText(context, "A2 mlb ", Toast.LENGTH_LONG).show();
                break;
        }
    }

}