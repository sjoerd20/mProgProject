package com.example.sjoerd.music4party.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sjoerd.music4party.FireBase;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.VideoRecyclerAdapter;
import com.example.sjoerd.music4party.YoutubeSearchRequest;
import com.example.sjoerd.music4party.activities.GroupCreatorHomeActivity;
import com.example.sjoerd.music4party.models.Playlist;
import com.example.sjoerd.music4party.models.Video;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.api.services.youtube.YouTube;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchFragment extends Fragment {

    private Activity activity;
    private Playlist retrievedPlaylist;
    private FireBase retrievedFireBase;

    private RecyclerView videoRecyclerView;
    private VideoRecyclerAdapter videoAdapter;
    private LinearLayoutManager horizontalLayoutManager;
    private ArrayList<Video> videos;
    private View fragmentView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        retrievedPlaylist = Playlist.getInstance(null);
        retrievedFireBase = FireBase.getInstance(false, 0);

        horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        fragmentView = inflater.inflate(R.layout.fragment_search, container, false);

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        // Create horizontal recyclerview for the videos currently in the playlist
        videoRecyclerView = getView().findViewById(R.id._creator_recycler_video_results);
        videoRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
        videoAdapter = new VideoRecyclerAdapter(getContext(), retrievedPlaylist.getPlaylist());
        videoRecyclerView.setLayoutManager(horizontalLayoutManager);
        videoRecyclerView.setAdapter(videoAdapter);

//        Toast.makeText(activity, "onSearchButtonClicked successful", Toast.LENGTH_LONG).show();
    }

    public void getSearchResults(ArrayList<Video> videosList) {

    }
}