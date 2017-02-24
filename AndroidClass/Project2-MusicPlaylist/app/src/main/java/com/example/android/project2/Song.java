package com.example.android.project2;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by zhang4all on 2/15/2017.
 */


public class Song implements Parcelable{
    private String songName;
    private String artistName;
    private String wikipediaSongURL;
    private String wikipediaArtistURL;
    private String youtubeURL;
    private int imageResourceID;


    /**
     * constructor for Song class
     * @param song name of song
     * @param artist name of artist
     * @param youtubeURLInput youtube address of song
     * @param wikiSongURL address of wikipedia page of song address
     * @param wikiArtistURL address of wikipedia page of artist
     * @param imageID resource ID for image of song cover
     */
    public Song(String song, String artist, String youtubeURLInput, String wikiSongURL, String wikiArtistURL, int imageID) {
        songName = song;
        artistName = artist;
        wikipediaSongURL = wikiSongURL;
        wikipediaArtistURL = wikiArtistURL;
        youtubeURL = youtubeURLInput;
        imageResourceID = imageID;
    }


    /**
     * constructor for the receiving activity
     * all the member variables are set from the values inside the parcel
     * @param in is the parcel that was sent to the receiving activity
     */
    public Song(Parcel in) {
        songName = in.readString();
        artistName = in.readString();
        wikipediaSongURL = in.readString();
        wikipediaArtistURL = in.readString();
        youtubeURL = in.readString();
        imageResourceID = in.readInt();
    }


    /**
     * This documentation is copied from android developer website
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation
     * @return a bitmask indicating the set of special object types marshaled by this Parcelable object instance
     */
    @Override
    public int describeContents() {
        return 0;
    }


    /**
     * This documentation is copied from the android developer website
     * flattens the object into a parcel
     * @param out this is the destination
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(songName);
        out.writeString(artistName);
        out.writeString(wikipediaSongURL);
        out.writeString(wikipediaArtistURL);
        out.writeString(youtubeURL);
        out.writeInt(imageResourceID);
    }


    /**
     * define anonymous class CREATOR by implementing the interface Parcelable.Creator<T>
     * this class generates instances of my Song class from a parcel
     * CREATOR is a nested class
     */
    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {


        /**
         * creates new instance of the parcelable class
         * @param source is the parcel to read the object's data from
         * @return a new instance of the parcelable class
         */
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }


        /**
         *
         * @param size of the array
         * @return a Song array with every entry initialized to null
         */
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };


    public String getSongName() {
        return songName;
    }


    public String getArtistName() {
        return artistName;
    }


    public String getWikipediaSongURL() {
        return wikipediaSongURL;
    }


    public String getWikipediaArtistURL() {
        return wikipediaArtistURL;
    }


    public String getYoutubeURL() { return youtubeURL; }


    public int getImageResourceID() { return imageResourceID; }
}
