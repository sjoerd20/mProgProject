/*
  This file handles the search of youtube videos. It interacts with the Youtube Data API

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party;

import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.firebase.database.core.Tag;

import org.apache.http.auth.AUTH;

import java.io.IOException;
import java.util.List;

public class YoutubeSearch {
    private static final long NUMBER_OF_RESULTS = 5;
    private YouTube youTube;
    private static final String API_KEY = "AIzaSyB91qfzy3kS3ZjtK4YoJ7Wa78afJyY7OHQ ";
    private String url = "https://www.googleapis.com/youtube/v3/search?part=id&q=tuto&type=video&key={YOUR_API_KEY}";

    private NetHttpTransport netHttpTransport = new NetHttpTransport();
    private JacksonFactory jacksonFactory = new JacksonFactory();

    public YoutubeSearch() {
    }

    public List<SearchResult> search(String searchText) {
        try {
            youTube = new YouTube.Builder(netHttpTransport, jacksonFactory, new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("Music4Party").build();


            YouTube.Search.List search = youTube.search().list("id,snippet");
            search.setKey(API_KEY);
            search.setQ(searchText);
            search.setType("video");
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_RESULTS);

            SearchListResponse searchListResponse = search.execute();
            List<SearchResult> searchResultList = searchListResponse.getItems();
            if (searchResultList != null) {
                return searchResultList;
            }
        } catch (GoogleJsonResponseException e) {
            Log.e("GoogleJsonError", " " + e.getDetails().getMessage());
        } catch (IOException e) {
            Log.e("IOException", e.getMessage());
        } catch (Throwable t) {
            Log.e("ThrowableError", " " + t.getMessage() + t.getCause());
        }
        return null;
    }
}
