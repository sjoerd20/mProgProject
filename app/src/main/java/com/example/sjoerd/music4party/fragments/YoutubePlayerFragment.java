/*
  Contains the youtubeFragmentPlayer

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.fragments;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.models.Playlist;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class YoutubePlayerFragment implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "AIzaSyB91qfzy3kS3ZjtK4YoJ7Wa78afJyY7OHQ ";
    private Playlist playlist;
    private static final String VIDEO_CODE = "FoCG-WNsZio";
    private static final String VIDEO_CODE2 = "8ebRM6vSYXw";

    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private Activity activity;
    private FragmentManager supportFragmentManager;
    private YouTubePlayer youTubePlayer;

    /*
     * Constructor. Creates a YoutubePlayerSupportFragment to play the videos
     */
    public YoutubePlayerFragment(Activity activity, FragmentManager supportFragmentManager, Playlist playlist) {
        this.activity = activity;
        this.supportFragmentManager = supportFragmentManager;
        this.playlist = playlist;
//        video_codes.add("FoCG-WNsZio");
//        video_codes.add("8ebRM6vSYXw");

        // Initialize youtubePlayer
        youTubePlayerSupportFragment = (YouTubePlayerSupportFragment) supportFragmentManager.
                findFragmentById(R.id.youtube_player_fragment);
        if (youTubePlayerSupportFragment == null) {
            return;
        }
        youTubePlayerSupportFragment.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer,
                                        boolean wasRestored) {
        if (!wasRestored) {
            this.youTubePlayer = youTubePlayer;

            // Set player to default style
            // CHROMELESS, DEFAULT, MINIMAL
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            youTubePlayer.setShowFullscreenButton(false);

            // Cue video
            youTubePlayer.loadVideo(VIDEO_CODE);

            // add listeners
            youTubePlayer.setPlaybackEventListener(new VideoPlaybackListener());
            youTubePlayer.setPlayerStateChangeListener(new PlayerStateChangeListener());
        }
        else {
            youTubePlayer.play();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        Log.e(TAG, "Youtube Player View initialization failed");
    }

    public class PlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
        @Override
        public void onLoading() {
        }

        @Override
        public void onLoaded(String s) {
            youTubePlayer.play();
        }

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onVideoStarted() {
        }

        @Override
        public void onVideoEnded() {
            // Cue next video
            youTubePlayer.loadVideo(playlist.retrieveVideo().getVideoId());
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
        }
    }

    public class VideoPlaybackListener implements YouTubePlayer.PlaybackEventListener {
        @Override
        public void onPlaying() {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onStopped() {
            youTubePlayer.play();
        }

        @Override
        public void onBuffering(boolean b) {
        }

        @Override
        public void onSeekTo(int i) {
        }
    }

    public void newVideo(String videoId) {
        youTubePlayer.loadVideo(videoId);
    }
}
