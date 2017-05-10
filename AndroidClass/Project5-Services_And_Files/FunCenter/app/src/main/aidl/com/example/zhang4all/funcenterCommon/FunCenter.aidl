// FunCenter.aidl
package com.example.zhang4all.funcenterCommon;

// Declare any non-default types here with import statements

interface FunCenter {

    Bitmap getPicBitmap(int anInt);

    void playSong(int songNum);

    void pauseSong();

    void resumeSong();

    void stopSong();
}
