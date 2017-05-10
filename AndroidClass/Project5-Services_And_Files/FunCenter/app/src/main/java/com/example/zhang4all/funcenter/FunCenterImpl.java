package com.example.zhang4all.funcenter;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.example.zhang4all.funcenterCommon.FunCenter;

/**
 * Created by zhang4all on 4/22/17.
 */


public class FunCenterImpl extends Service {

    private class FunImpl extends FunCenter.Stub {

        MediaPlayer mp;


        @Override
        public Bitmap getPicBitmap(int picNum) throws RemoteException {

            Bitmap bitmap = null;

            Drawable drawablePic1 = ContextCompat.getDrawable(getApplicationContext(), R.drawable.harley);
            Drawable drawablePic2 = ContextCompat.getDrawable(getApplicationContext(), R.drawable.goku);
            Drawable drawablePic3 = ContextCompat.getDrawable(getApplicationContext(), R.drawable.zamasu);

            if (picNum == 1) {
                bitmap = ((BitmapDrawable) drawablePic1).getBitmap();
            }
            else if (picNum == 2) {
                bitmap = ((BitmapDrawable) drawablePic2).getBitmap();
            }
            else if (picNum == 3) {
                bitmap = ((BitmapDrawable) drawablePic3).getBitmap();
            }

            return bitmap;
        }


        @Override
        public void playSong(int songNum) throws RemoteException {

            // stop current song if already playing
            if ((mp != null) && mp.isPlaying()) {
                mp.stop();
            }

            // assign the correct song to mediaplayer mp
            if (songNum == 1) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.song_1);
            }
            else if (songNum == 2) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.song_2);
            }
            else if (songNum == 3) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.song_3);
            }

            mp.start();
        }


        @Override
        public void pauseSong() throws RemoteException {

            if (mp == null) {
                return;
            }

            synchronized (mp) {
                if (mp != null) {
                    mp.pause();
                }
            }
        }


        @Override
        public void resumeSong() throws RemoteException {

            if (mp == null) {
                return;
            }

            synchronized (mp) {
                if (mp != null) {
                    mp.start();
                }
            }
        }


        @Override
        public void stopSong() throws RemoteException {

            if (mp == null) {
                return;
            }

            synchronized (mp) {
                if (mp != null) {
                    mp.stop();
                }
            }
        }
    }


    private FunImpl funImpl = new FunImpl();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return funImpl;
    }
}
