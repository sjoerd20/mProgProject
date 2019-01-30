/*
  Home activity for the group creator. This activity contains a Youtube video in a fragment,
  a list with the next videos and an search option to find new videos

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.sjoerd.music4party.FireBase;
import com.example.sjoerd.music4party.VideoAdapter;
import com.example.sjoerd.music4party.VideoRecyclerAdapter;
import com.example.sjoerd.music4party.YoutubeSearchRequest;
import com.example.sjoerd.music4party.fragments.PlaylistFragment;
import com.example.sjoerd.music4party.fragments.SearchFragment;
import com.example.sjoerd.music4party.models.Group;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.fragments.YoutubePlayerFragment;
import com.example.sjoerd.music4party.models.Playlist;
import com.example.sjoerd.music4party.models.Video;

import java.util.ArrayList;
import java.util.List;

public class GroupCreatorHomeActivity extends AppCompatActivity implements YoutubeSearchRequest.Callback {

    private static final String TAG = GroupCreatorHomeActivity.class.getSimpleName();
    private YoutubePlayerFragment youTubePlayerFragment;
    private Group retrievedGroup;
    private FireBase retrievedFireBase;
    private Playlist retrievedPlaylist;
    private SearchFragment searchFragment;
    private PlaylistFragment playlistFragment;
    private VideoAdapter videoAdapter;
    private ListView listViewResults;

//    OnFragmentInteractionListener fragmentInteractionListener;
//
//    // Callback for communicating with fragment
//    public interface OnFragmentInteractionListener {
//        void onSearchResult(ArrayList<Video> videos);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator_home);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Add listener to listview of video results
        listViewResults = findViewById(R.id._listViewVideosResult);
        listViewResults.setOnItemClickListener(new OnVideoClicked());

        // Initialize fragments
        searchFragment = new SearchFragment();
        playlistFragment = new PlaylistFragment();

        // Setup fragmentmanager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentLayout, new SearchFragment());
        fragmentTransaction.commit();

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.loginToolbar);
        setActionBar(toolbar);

        // Receive intent
        Intent intent = getIntent();
        if (retrievedGroup == null) {
            retrievedGroup = (Group) intent.getSerializableExtra("group");
            retrievedPlaylist = (Playlist) intent.getSerializableExtra("playlist");

            Toast.makeText(this, retrievedGroup.getGroupId(), Toast.LENGTH_LONG).show();
        }
        retrievedFireBase = FireBase.getInstance(false, retrievedGroup.getLoginCode());

        // Initiate youtubePlayerFragment
        youTubePlayerFragment = new YoutubePlayerFragment(this, getSupportFragmentManager(), retrievedPlaylist);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onPlaylistButtonClicked(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, playlistFragment);
        fragmentTransaction.commit();
//        Toast.makeText(this, "onPlaylistButtonClicked successful", Toast.LENGTH_LONG).show();
    }

    public void onSearchButtonClicked(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, searchFragment);
        fragmentTransaction.commit();
//        Toast.makeText(this, "onSearchButtonClicked successful", Toast.LENGTH_LONG).show();
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
     * If successful found a video, play it
     */
    @Override
    public void gotVideos(ArrayList<Video> videos) {
        try {

            // Show result in SearchFragment
            searchFragment.getSearchResults(videos);

            videoAdapter = new VideoAdapter(this, R.layout.video_item, videos);
            listViewResults.setAdapter(videoAdapter);

            // Update playlist
            retrievedPlaylist.removeVideo();
            retrievedPlaylist.addVideo(videos.get(0));
            retrievedFireBase.changePlaylist(retrievedPlaylist);
        }
        catch(IndexOutOfBoundsException e) {
            Toast.makeText(this, "No matching videos found", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this, "done!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotVideosError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class OnVideoClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Update playlist
            Video clickedVideo = (Video) adapterView.getItemAtPosition(i);
            listViewResults.setAdapter(null); // Empty adapter (remove results)
            retrievedPlaylist.removeVideo();
            retrievedPlaylist.addVideo(clickedVideo);
            retrievedFireBase.changePlaylist(retrievedPlaylist);
        }
    }
}
