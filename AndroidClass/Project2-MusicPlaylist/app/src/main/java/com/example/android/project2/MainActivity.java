package com.example.android.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SongAdapter adapter;
    private ListView listView;
    private ArrayList<Song> songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of Song objects
        songs = new ArrayList<>();
        songs.add(new Song("Sorry, Blame It On Me", "Akon", "https://www.youtube.com/watch?v=ynMk2EwRi4Q", "https://en.wikipedia.org/wiki/Sorry,_Blame_It_on_Me", "https://en.wikipedia.org/wiki/Akon", R.drawable.sorry_blame_it_on_me));
        songs.add(new Song("I'm Like a Bird", "Nelly Furtado", "https://www.youtube.com/watch?v=roPQ_M3yJTA", "https://en.wikipedia.org/wiki/I'm_Like_a_Bird", "https://en.wikipedia.org/wiki/Nelly_Furtado", R.drawable.im_like_a_bird));
        songs.add(new Song("Sail", "Awolnation", "https://www.youtube.com/watch?v=tgIqecROs5M", "https://en.wikipedia.org/wiki/Sail_(song)", "https://en.wikipedia.org/wiki/Awolnation", R.drawable.sail));
        songs.add(new Song("I Need Your Love", "Calvin Harris", "https://www.youtube.com/watch?v=AtKZKl7Bgu0", "https://en.wikipedia.org/wiki/I_Need_Your_Love_(Calvin_Harris_song)", "https://en.wikipedia.org/wiki/Calvin_Harris", R.drawable.i_need_your_love));
        songs.add(new Song("Titanium", "David Guetta", "https://www.youtube.com/watch?v=JRfuAukYTKg", "https://en.wikipedia.org/wiki/Titanium_(song)", "https://en.wikipedia.org/wiki/David_Guetta", R.drawable.titanium));
        songs.add(new Song("The Final Countdown", "Europe", "https://www.youtube.com/watch?v=9jK-NcRmVcw", "https://en.wikipedia.org/wiki/The_Final_Countdown_(song)", "https://en.wikipedia.org/wiki/Europe_(band)", R.drawable.the_final_countdown));

        adapter = new SongAdapter(this, songs);

        // this selects the listView from the xml
        listView = (ListView) findViewById(R.id.list);

        // this hooks up the adapter to the listview
        listView.setAdapter(adapter);
    }


    /**
     * when this button is clicked, the PlaylistActivity is loaded
     * @param view
     */
    public void createPlaylistButton(View view) {
        int numItems = listView.getAdapter().getCount();
        Intent playlistIntent = new Intent(MainActivity.this, PlaylistActivity.class);
        ArrayList<Song> checkedSongs = new ArrayList<>();
        for (int i=0; i<numItems; i++) {
            View item = listView.getChildAt(i);
            if (item != null) {
                CheckBox checkbox = (CheckBox) item.findViewById(R.id.checkbox);
                if (checkbox.isChecked()) { checkedSongs.add(songs.get(i)); }
            }
        }
        playlistIntent.putParcelableArrayListExtra("arrayListOfSongs", checkedSongs);
        if (checkedSongs.size() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Must select at least one song in order to create a playlist", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        startActivity(playlistIntent);
    }


    /**
     * this method inflates the options menu from the xml file
     * @param menu
     * @return a boolean indicating whether the menu is displayed
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    /**
     * implements the action based on the item selected in the menu
     * @param item this is the menu item that was selected
     * @return false to allow normal menu processing to proceed, true means it is done processing this menu selection
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int numItems = listView.getAdapter().getCount();
        switch (item.getItemId()) {
            case R.id.create_playlist:
                Intent playlistIntent = new Intent(MainActivity.this, PlaylistActivity.class);
                ArrayList<Song> checkedSongs = new ArrayList<>();
                for (int i=0; i<numItems; i++) {
                    View view = listView.getChildAt(i);
                    if (view != null) {
                        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
                        if (checkbox.isChecked()) { checkedSongs.add(songs.get(i)); }
                    }
                }
                playlistIntent.putParcelableArrayListExtra("arrayListOfSongs", checkedSongs);
                if (checkedSongs.size() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Must select at least one song in order to create a playlist", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;
                }
                startActivity(playlistIntent);
                return true;
            case R.id.clear_all:
                for (int i=0; i<numItems; i++) {
                    View view = listView.getChildAt(i);
                    if (view != null) {
                        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
                        checkbox.setChecked(false);
                    }
                }
                return true;
            case R.id.invert:
                for (int i=0; i<numItems; i++) {
                    View view = listView.getChildAt(i);
                    if (view != null) {
                        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
                        if (checkbox.isChecked()) { checkbox.setChecked(false); }
                        else { checkbox.setChecked(true); }
                    }
                }
                return true;
            case R.id.check_all:
                for (int i=0; i<numItems; i++) {
                    View view = listView.getChildAt(i);
                    if (view != null) {
                        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
                        checkbox.setChecked(true);
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
