package com.example.sjoerd.music4party;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sjoerd.music4party.models.Video;

import java.util.List;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.VideoViewHolder> {
    private Context context;
    private List<Video> videoList;

    public VideoRecyclerAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoRecyclerAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the layout file
        View videoView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                                        horizontal_list_video_item, viewGroup, false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(videoView);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoRecyclerAdapter.VideoViewHolder videoViewHolder, int pos) {
        videoViewHolder.videoTitleView.setText(videoList.get(pos).getVideoTitle());
        videoViewHolder.videoTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoTitle = videoList.get(videoViewHolder.getAdapterPosition()).getVideoTitle();
                Toast.makeText(context, videoTitle + " is selected", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitleView;

        public VideoViewHolder(View view) {
            super(view);
            videoTitleView = view.findViewById(R.id.videoTitle);
        }
    }
}
