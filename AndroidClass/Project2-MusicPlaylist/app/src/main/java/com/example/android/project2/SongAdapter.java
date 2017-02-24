package com.example.android.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zhang4all on 2/14/2017.
 */

public class SongAdapter extends ArrayAdapter<Song>{

    /**
     * constructor to create the SongAdapter that connects the ListView to the layout
     * @param context current context
     * @param songs an arraylist of Song objects
     */
    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, R.layout.song_list_item, songs);
    }


    /**
     * this displays the data at the specific position
     * calls getView() on every position in the ArrayList
     * @param position is the specified position in the dataset
     * @param convertView is the old view to reuse
     * @param parent is the parent that this view will eventually be attached to
     * @return a View corresponding to the data at the specified position
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // checks if old view can be reused
        View newView = convertView;
        // next four lines of code follow same format as Udacity Android Course
        if (newView == null) {
            // if there is no old view, we inflate a new view
            newView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_list_item, parent, false);
        }

        // gets the current song from the arraylist that was passed into the adapter
        String currentSong = getItem(position).getSongName();

        // find textview in the song_list_item.xml
        TextView songTextView = (TextView) newView.findViewById(R.id.song_name);
        // set the song in the textview
        songTextView.setText(currentSong);

        return newView;
    }
}
