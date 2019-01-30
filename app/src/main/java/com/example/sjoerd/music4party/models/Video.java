/*
  Contains all info about a video

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.models;

public class Video {
    private String videoTitle;
    private String videoId;
    private String thumbnailURL;

    public Video(String videoTitle, String videoId) {
        this.videoTitle = videoTitle;
        this.videoId = videoId;
    }

    public Video(String videoTitle, String videoId, String thumbnailURL) {
        this.videoTitle = videoTitle;
        this.videoId = videoId;
        this.thumbnailURL = thumbnailURL;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
