/*
  Home activity for the group creator. This activity contains a Youtube video in a fragment,
  a list with the next videos and an search option to find new videos

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sjoerd.music4party.FireBase;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.VideoAdapter;
import com.example.sjoerd.music4party.YoutubeSearchRequest;
import com.example.sjoerd.music4party.fragments.PlaylistFragment;
import com.example.sjoerd.music4party.fragments.SearchFragment;
import com.example.sjoerd.music4party.fragments.YoutubePlayerFragment;
import com.example.sjoerd.music4party.models.Group;
import com.example.sjoerd.music4party.models.Playlist;
import com.example.sjoerd.music4party.models.Video;

import java.util.ArrayList;

public class GroupCreatorHomeActivity extends AppCompatActivity implements YoutubeSearchRequest.Callback {

    private Group retrievedGroup;
    private FireBase retrievedFireBase;
    private Playlist retrievedPlaylist;

    private SearchFragment searchFragment;
    private PlaylistFragment playlistFragment;
    private VideoAdapter videoAdapter;
    private ListView listViewVideos;

    /*
     * Set up a toolbar, a listview for showing videos and the following fragments:
     * - YoutubePlayerFragment (for playing youtube video)
     * - SearchFragment (for searching videos)
     * - PlaylistFragment (for viewing current playlist)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator_home);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);

        // Receive intent
        Intent intent = getIntent();
        if (retrievedGroup == null) {
            retrievedGroup = (Group) intent.getSerializableExtra("group");
            retrievedPlaylist = (Playlist) intent.getSerializableExtra("playlist");
        }
        retrievedFireBase = FireBase.getInstance(false, retrievedGroup.getLoginCode());

        // Add listener to listview of video results
        listViewVideos = findViewById(R.id._listViewVideos);
        listViewVideos.setOnItemClickListener(new OnVideoClicked());

        // Initialize fragments
        searchFragment = new SearchFragment();
        playlistFragment = new PlaylistFragment();

        // Setup fragmentmanager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentLayout, new SearchFragment());
        fragmentTransaction.commit();

        // Add a first video to the playlist as default
        Video video = new Video("Mariage d'Amour - Paul de Senneville || Jacob's Piano",
                                "FoCG-WNsZio",
                                "https://i.ytimg.com/vi/FoCG-WNsZio/default.jpg");
        retrievedPlaylist.addVideo(video);

        // Initiate youtubePlayerFragment
        YoutubePlayerFragment youTubePlayerFragment = new YoutubePlayerFragment(this,
                getSupportFragmentManager(), retrievedPlaylist, retrievedFireBase);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    /*
     * Inflate SearchFragment and enable OnVideoClicked listener
     */
    public void onPlaylistButtonClicked(View view) {

        // Set playlist fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, playlistFragment);
        fragmentTransaction.commit();

        // Show current playlist
        videoAdapter = new VideoAdapter(this, R.layout.video_item,
                                        retrievedPlaylist.getPlaylist());
        listViewVideos.setAdapter(videoAdapter);

        // Disable click listener
        listViewVideos.setOnItemClickListener(null);
        listViewVideos.setClickable(false);
    }

    /*
     * Inflate SearchFragment and enable OnVideoClicked listener
     */
    public void onSearchButtonClicked(View view) {

        // Set search fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, searchFragment);
        fragmentTransaction.commit();

        // Empty adapter (remove current playlist)
        listViewVideos.setAdapter(null);

        // Enable click listener
        listViewVideos.setClickable(true);
        listViewVideos.setOnItemClickListener(new OnVideoClicked());
    }

    /*
     * Inflate options in toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    /*
     * Extract the user input of the toolbar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_settings:
                Intent intentSettings = new Intent(GroupCreatorHomeActivity.this,
                                                    SettingsActivity.class);
                intentSettings.putExtra("group", retrievedGroup);
                startActivity(intentSettings);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /*
     * Search videos with the Youtube Data API using a keyword input from the user
     */
    public void onSearchClicked(View view) {
        EditText searchTextView = findViewById(R.id.searchText);
        String searchText = searchTextView.getText().toString();
        if (!searchText.equals("")) {
            YoutubeSearchRequest youtubeSearchRequest = new YoutubeSearchRequest(this);
            youtubeSearchRequest.getVideos(this, searchText);
        }
    }

    /*
     * If successful found videos, display it in an adapter
     */
    @Override
    public void gotVideos(ArrayList<Video> videos) {
        try {
            videoAdapter = new VideoAdapter(this, R.layout.video_item, videos);
            listViewVideos.setAdapter(videoAdapter);
        }
        catch(IndexOutOfBoundsException e) {
            Toast.makeText(this, "No matching videos found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void gotVideosError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*
     * Store the selected video in the playlist
     */
    private class OnVideoClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Update playlist
            Video clickedVideo = (Video) adapterView.getItemAtPosition(i);

            // Empty adapter (remove current results)
            listViewVideos.setAdapter(null);

            // Update playlist
            retrievedPlaylist.addVideo(clickedVideo);
            retrievedFireBase.changePlaylist(retrievedPlaylist);
            retrievedPlaylist.getPlaylist();
        }
    }
}
