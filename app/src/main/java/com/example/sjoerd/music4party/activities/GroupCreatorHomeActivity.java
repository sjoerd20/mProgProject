/*
  Home activity for the group creator. This activity contains a Youtube video in a fragment,
  a list with the next videos and an search option to find new videos

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sjoerd.music4party.VideoRecyclerAdapter;
import com.example.sjoerd.music4party.YoutubeSearchRequest;
import com.example.sjoerd.music4party.models.Group;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.fragments.YoutubePlayerFragment;
import com.example.sjoerd.music4party.models.Video;

import java.util.ArrayList;
import java.util.List;

public class GroupCreatorHomeActivity extends AppCompatActivity implements YoutubeSearchRequest.Callback {

    private static final String TAG = GroupCreatorHomeActivity.class.getSimpleName();
    private YoutubePlayerFragment youTubePlayerFragment;
    private Group retrievedGroup;
    private List<Video> videoList = new ArrayList<>();
    private RecyclerView videoRecyclerView;
    private VideoRecyclerAdapter videoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator_home);

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);

        // Receive intent
        Intent intent = getIntent();
        if (retrievedGroup == null) {
            retrievedGroup = (Group) intent.getSerializableExtra("group");
            Toast.makeText(this, retrievedGroup.getGroupId(), Toast.LENGTH_LONG).show();
        }

        // Initiate youtubePlayerFragment
        youTubePlayerFragment = new YoutubePlayerFragment(this, getSupportFragmentManager());

        // Create horizontal recyclerview for the videos currently in the playlist
        videoRecyclerView = findViewById(R.id._creator_recycler_video);
        videoRecyclerView.addItemDecoration(new DividerItemDecoration(GroupCreatorHomeActivity.this, LinearLayoutManager.HORIZONTAL));
        videoAdapter = new VideoRecyclerAdapter(getApplicationContext(), videoList);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(GroupCreatorHomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        videoRecyclerView.setLayoutManager(horizontalLayoutManager);
        videoRecyclerView.setAdapter(videoAdapter);

        videoList.add(new Video("Einaudi", " "));
        videoList.add(new Video("Jacob's piano", " "));
        videoList.add(new Video("Queen", " "));
        videoList.add(new Video("Bohemian Rhapsody", " "));
        videoList.add(new Video("Pachelbell", " "));
        videoList.add(new Video("Hey brother", " "));

    }

    // inflate options in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    // extract the user input of the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_settings:
                Intent intentSettings = new Intent(GroupCreatorHomeActivity.this,
                                                    SettingsActivity.class);
                intentSettings.putExtra("group", retrievedGroup);
                startActivity(intentSettings);
                return true;
            case R.id.action_search:
                Intent intentSearch = new Intent(GroupCreatorHomeActivity.this,
                                                    SearchActivity.class);
                startActivity(intentSearch);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    // Search onClick method
    public void onSearchClicked(View view) {
        EditText searchTextView = findViewById(R.id.creatorSearchText);
        String searchText = searchTextView.getText().toString();
        if (!searchText.equals("")) {
            YoutubeSearchRequest youtubeSearchRequest = new YoutubeSearchRequest(this);
            youtubeSearchRequest.getVideos(this, searchText);
        }
    }

    @Override
    public void gotVideos(ArrayList<Video> videos) {
        youTubePlayerFragment.newVideo(videos.get(0).getVideoId());
        Toast.makeText(this, "done!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotVideosError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
