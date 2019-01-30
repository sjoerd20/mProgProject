package com.example.sjoerd.music4party.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sjoerd.music4party.FireBase;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.YoutubeSearchRequest;
import com.example.sjoerd.music4party.activities.GroupCreatorHomeActivity;
import com.example.sjoerd.music4party.models.Playlist;
import com.example.sjoerd.music4party.models.Video;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private Activity activity;
    private Playlist retrievedPlaylist;
    private FireBase retrievedFireBase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        activity = (GroupCreatorHomeActivity) getActivity();
        retrievedPlaylist = Playlist.getInstance(null);
        retrievedFireBase = FireBase.getInstance(false, 0);

        Toast.makeText(activity, "onSearchButtonClicked successful", Toast.LENGTH_LONG).show();
    }
}