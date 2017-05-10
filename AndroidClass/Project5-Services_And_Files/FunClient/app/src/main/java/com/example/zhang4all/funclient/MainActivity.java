package com.example.zhang4all.funclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhang4all.funcenterCommon.FunCenter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "FunServiceUser";
    // FunCenter is the proxy
    private FunCenter funCenterService;
    private boolean mIsBound = false;

    // declare view elements here
    static ImageView image;
    static Button goButton;
    static Button playButton;
    static Button pauseButton;
    static Button resumeButton;
    static Button stopButton;
    static EditText picNumberEditText;
    static EditText songNumberEditText;
    static Button eraseListButton;

    private final static String fileName = "Requests.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewElements();

        updateLog();

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String picNumberString = picNumberEditText.getText().toString();
                int picNumberInt;

                try {
                    picNumberInt = Integer.parseInt(picNumberString);

                    if ((picNumberInt < 1) || (picNumberInt > 3)) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                    Toast toast = Toast.makeText(getApplicationContext(), "Picture number must be an integer between 1 and 3", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Log.i("pic number:", Integer.toString(picNumberInt));

                try {
                    // fun center and output the string
                    if (mIsBound) {

                        Bitmap b = funCenterService.getPicBitmap(picNumberInt);

                        image.setImageBitmap(b);

                        writeFile("requested image " + Integer.toString(picNumberInt));
                        updateLog();
                    }
                    else {
                        Log.i(TAG, "Ugo says that the service was not bound!");
                    }

                } catch (Exception e) {

                    Log.e(TAG, e.toString());
                }
            }
        });


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // can't play if no number is inputted
                String songNumberString = songNumberEditText.getText().toString();
                int songNumberInt;
                try {
                    songNumberInt = Integer.parseInt(songNumberString);

                    if ((songNumberInt < 1) || (songNumberInt > 3)) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Song number must be an integer between 1 and 3", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                try {
                    // fun center and output the string
                    if (mIsBound) {
                        // this statement is calling the method from the service
                        funCenterService.playSong(songNumberInt);

                        writeFile("play song " + Integer.toString(songNumberInt));
                        updateLog();
                    }
                    else {
                        Log.i(TAG, "Ugo says that the service was not bound!");
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, e.toString());
                }
            }
        });


        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // fun center and output the string
                    if (mIsBound) {
                        // this statement is calling the method from the service
                        funCenterService.pauseSong();

                        writeFile("paused song");
                        updateLog();
                    }
                    else {
                        Log.i(TAG, "Ugo says that the service was not bound!");
                    }
                } catch (RemoteException e) {

                    Log.e(TAG, e.toString());
                }
            }
        });


        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // fun center and output the string
                    if (mIsBound) {
                        // this statement is calling the method from the service
                        funCenterService.resumeSong();

                        writeFile("resumed song");
                        updateLog();
                    }
                    else {
                        Log.i(TAG, "Ugo says that the service was not bound!");
                    }
                } catch (RemoteException e) {

                    Log.e(TAG, e.toString());
                }
            }
        });


        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // fun center and output the string
                    if (mIsBound) {
                        // this statement is calling the method from the service
                        funCenterService.stopSong();

                        writeFile("stopped song");
                        updateLog();
                    }
                    else {
                        Log.i(TAG, "Ugo says that the service was not bound!");
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, e.toString());
                }
            }
        });


        eraseListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearFile();
                updateLog();
            }
        });
    }


    /**
     * initialize all the view elements of the activity
     */
    void initializeViewElements() {
        image = (ImageView) findViewById(R.id.image);
        goButton = (Button) findViewById(R.id.go_button);
        playButton = (Button) findViewById(R.id.play_button);
        pauseButton = (Button) findViewById(R.id.pause_button);
        resumeButton = (Button) findViewById(R.id.resume_button);
        stopButton = (Button) findViewById(R.id.stop_button);
        picNumberEditText = (EditText) findViewById(R.id.picNumber);
        songNumberEditText = (EditText) findViewById(R.id.songNumber);
        eraseListButton = (Button) findViewById(R.id.erase_list_button);
    }


    /**
     * writes a string to the logging file
     * @param string the message to write to the persisted log
     */
    public void writeFile(String string) {
        string = string + "\n";

        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_APPEND);
            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, "IOException");
        }
    }


    /**
     * empty the file
     */
    public void clearFile() {

        deleteFile(fileName);
    }


    /**
     * update the UI for the log
     */
    private void updateLog() {
        ArrayList<String> requests = new ArrayList<>();

        // read from file
        String line;
        try {
            FileInputStream fis = openFileInput(fileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            line = br.readLine();

            while (null != line) {
                requests.add(line);
                Log.i(TAG, "adding to request here");
                line = br.readLine();
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException");
        }

        ArrayAdapter<String> requestsAdapter = new ArrayAdapter<>(this, R.layout.request_list_item, R.id.request, requests);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(requestsAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!mIsBound) {

            boolean b;
            Intent i = new Intent(FunCenter.class.getName());

            // Must make intent explicit or lower target API level to 19.
            ResolveInfo info = getPackageManager().resolveService(i, Context.BIND_AUTO_CREATE);
            i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));

            b = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
            if (b) {
                Log.i(TAG, "Ugo says bindService() succeeded!");
            } else {
                Log.i(TAG, "Ugo says bindService() failed!");
            }
        }
    }


    @Override
    protected void onPause() {

        Log.i(TAG, "onPause");

        super.onPause();
    }



    @Override
    protected void onDestroy() {

        Log.i(TAG, "onDestroy out");

        if (mIsBound) {

            try {
                funCenterService.stopSong();
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
            }

            Log.i(TAG, "onDestroy");

            unbindService(this.mConnection);
        }

        super.onDestroy();
    }


    /**
     * Creates an instance of ServiceConnection to be used to bind to the service
     */
    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            funCenterService = FunCenter.Stub.asInterface(iBinder);
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            funCenterService = null;
            mIsBound = false;
        }
    };
}
