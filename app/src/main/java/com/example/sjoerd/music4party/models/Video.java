/*
  Contains all info about a video

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.models;

public class Video {
    private String videoTitle;

    public Video(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
