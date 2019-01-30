package com.example.sjoerd.music4party;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sjoerd.music4party.models.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends ArrayAdapter<Video> {

    private ArrayList<Video> videos;

    // Constructor
    public VideoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Video> objects) {
        super(context, resource, objects);
        this.videos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.video_item, parent, false);
        }

        // Get current video
        Video video = videos.get(position);

        // Set video title and thumbnail
        TextView videoTitle = convertView.findViewById(R.id.videoTitle);
        videoTitle.setText(video.getVideoTitle());

        // draw image with picasso
        ImageView thumbnail = convertView.findViewById(R.id.thumbnailView);
        Picasso.with(getContext()).load(video.getThumbnailURL()).into(thumbnail);

        return convertView;
    }
}
