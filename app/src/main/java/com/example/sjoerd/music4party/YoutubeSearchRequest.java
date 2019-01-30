package com.example.sjoerd.music4party;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sjoerd.music4party.models.Video;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YoutubeSearchRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String API_KEY = "AIzaSyAn6SA9F2sxxSCeiWJK0Dq7v2oe8qbfcvQ";
    private int MAX_RESULTS = 5;

    private String url1 = "https://www.googleapis.com/youtube/v3/search?part=id%2Csnippet&" +
            "maxResults=" + MAX_RESULTS + "&q=";
    private String url2 = "&type=video&fields=items(id%2FvideoId%2Csnippet(thumbnails%2Fdefault%2Ctitle))&key=";
//    private String url_1 = "https://www.googleapis.com/youtube/v3/search?part=id,snippet&q=";
//    private String url_2 = "&type=video&key=";

    private Context context;
    private ArrayList<Video> videos = new ArrayList<>();
    Callback activity;

    public interface Callback {
        void gotVideos(ArrayList<Video> videos);
        void gotVideosError(String message);
    }

    // Constructor
    public YoutubeSearchRequest(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotVideosError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray itemsArray = response.getJSONArray("items");

            // for each video in items
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject videoObject = itemsArray.getJSONObject(i);

                // Get video id
                JSONObject id = videoObject.getJSONObject("id");
                String videoId = id.getString("videoId");

                // Get video thumbnail
                JSONObject snippet = videoObject.getJSONObject("snippet");
                String videoTitle = snippet.getString("title");
                JSONObject thumbnails = snippet.getJSONObject("thumbnails").getJSONObject("default");
                String thumbnailURL = thumbnails.getString("url");

                Video video = new Video(videoTitle, videoId, thumbnailURL);
                videos.add(video);
            }

            Log.i("Response", "successful");
            activity.gotVideos(videos);
        }
        catch(JSONException e) {
            Log.e("onResponseError", e.getMessage());
        }
    }

    public void getVideos(Callback activity, String searchText) {
        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = url1 + searchText + url2 + API_KEY;

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch(Exception e) {
            Log.e("requestError", e.getMessage());
        }
    }
}
