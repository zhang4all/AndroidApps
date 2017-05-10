package com.example.android.a3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zhang4all on 3/25/2017.
 */

public class A3Receiver extends BroadcastReceiver{


    /**
     * receives the broadcast from A1 to start the nba or mlb activity
     * @param context
     * @param intent is the intent sent by the broadcaster
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction() ) {
            case "com.example.android.a1nba":
                Intent nbaIntent = new Intent();
                nbaIntent.setClass(context, NBAActivity.class);
                nbaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(nbaIntent);
                break;
            case "com.example.android.a1mlb":
                Intent mlbIntent = new Intent();
                mlbIntent.setClass(context, MLBActivity.class);
                mlbIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mlbIntent);
                break;
        }
    }
}
