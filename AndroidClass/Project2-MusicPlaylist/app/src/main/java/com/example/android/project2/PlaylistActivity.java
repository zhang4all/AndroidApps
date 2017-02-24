package com.example.android.project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zhang4all on 2/15/2017.
 */

public class PlaylistActivity extends AppCompatActivity {
    private GridView gridView;
    private PlaylistAdapter adapter;
    private ArrayList<Song> songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        songs = getIntent().getParcelableArrayListExtra("arrayListOfSongs");

        adapter = new PlaylistAdapter(this, songs);

        // this selects the listView from the xml
        gridView = (GridView) findViewById(R.id.grid);

        // this hooks up the adapter to the gridView
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song song = songs.get(i);

                Uri uri = Uri.parse(song.getYoutubeURL());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        registerForContextMenu(gridView);
    }


    /**
     * this method inflates the options menu from the xml file
     * @param menu the context menu being built
     * @param v the view that the context menu is built in
     * @param menuInfo extra information about the item
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }


    /**
     * implements the action based on the item selected in the menu
     * @param item this is the menu item that was selected
     * @return false to allow normal menu processing to proceed, true means it is done processing this menu selection
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo data = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Song song = songs.get(data.position);
        Uri uri;
        Intent intent;

        switch (item.getItemId()) {
            case R.id.play_video_clip:
                uri = Uri.parse(song.getYoutubeURL());
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            case R.id.open_song_wiki:
                uri = Uri.parse(song.getWikipediaSongURL());
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            case R.id.open_artist_wiki:
                uri = Uri.parse(song.getWikipediaArtistURL());
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
        }

        return super.onContextItemSelected(item);
    }
}
