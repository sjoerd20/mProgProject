/*
  Contains the youtubeFragmentPlaye for playing the youtube video

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.fragments;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.sjoerd.music4party.FireBase;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.models.Playlist;
import com.example.sjoerd.music4party.models.Video;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;

import static android.content.ContentValues.TAG;

public class YoutubePlayerFragment implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "AIzaSyB91qfzy3kS3ZjtK4YoJ7Wa78afJyY7OHQ ";

    private YouTubePlayer youTubePlayer;

    private Playlist retrievedPlaylist;
    private FireBase retrievedFireBase;

    /*
     * Constructor. Creates a YoutubePlayerSupportFragment to play the videos
     */
    public YoutubePlayerFragment(Activity activity, FragmentManager supportFragmentManager,
                                 Playlist playlist, FireBase fireBase) {
        this.retrievedPlaylist = playlist;
        this.retrievedFireBase = fireBase;

        // Initialize youtubePlayer
        YouTubePlayerSupportFragment youTubePlayerSupportFragment = (YouTubePlayerSupportFragment)
                                                                    supportFragmentManager.
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

            // Set player to DEFAULT/MINIMAL style
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            youTubePlayer.setShowFullscreenButton(true);

            // Cue first video from playlist
            Video firstVideo = retrievedPlaylist.retrieveVideo();
            if (firstVideo != null) {
                youTubePlayer.loadVideo(firstVideo.getVideoId());
            }

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

            // Cue next video from playlist
            Video nextVideo = retrievedPlaylist.retrieveVideo();
            if (nextVideo != null) {
                youTubePlayer.loadVideo(nextVideo.getVideoId());
            }
            retrievedFireBase.changePlaylist(retrievedPlaylist);
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
}
