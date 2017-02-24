package com.example.android.project2;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;


/**
 * Created by zhang4all on 2/16/2017.
 */


public class PlaylistAdapter extends BaseAdapter {
    private static final int PADDING = 8;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 250;
    private Context mContext;
    private ArrayList<Song> mSongs;


    /**
     * constructor to create the PlaylistAdapter that connects the GridView to the layout
     * @param c the current context
     * @param songs the arraylist of Song objects
     */
    public PlaylistAdapter(Context c, ArrayList<Song> songs) {
        mContext = c;
        this.mSongs = songs;
    }


    /**
     *
     * @return the number of items in the data set
     */
    @Override
    public int getCount() {
        return mSongs.size();
    }


    /**
     *
     * @param position the position in the dataset
     * @return the Song object in the dataset
     */
    @Override
    public Song getItem(int position) {
        return mSongs.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    /**
     * this displays the data at the specific position
     * calls getView() on every position in the ArrayList
     * @param position is the specified position in the dataset
     * @param convertView is the old view to reuse
     * @param Parent is the parent that this view will eventually be attached to
     * @return a View corresponding to the data at the specified position
     */
    @Override
    public View getView(int position, View convertView, ViewGroup Parent) {
        // checks if old view can be reused
        ImageView imageView = (ImageView) convertView;

        if (imageView == null) {
            imageView = new ImageView((mContext));
            imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
            imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        imageView.setImageResource(mSongs.get(position).getImageResourceID());

        Log.i("image resource id: ", mSongs.get(position).getSongName());

        return imageView;
    }
}
