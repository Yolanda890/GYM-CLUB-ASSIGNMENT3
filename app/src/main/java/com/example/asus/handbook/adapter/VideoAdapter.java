package com.example.asus.handbook.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.asus.handbook.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<String> myList;
    private int rowLayout;
    private Context mContext;

    public VideoAdapter(List<String> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }


    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new VideoAdapter.ViewHolder(v);  //this is the major change here.
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String entry = myList.get(i);
        viewHolder.myName.setText(entry);
        viewHolder.myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // viewHolder.myVideo.start();
            }
        });
        //mContext.getDrawable(country.getImageResourceId(mContext))
        //设置视频控制器
        //viewHolder.myVideo.setMediaController(new MediaController(mContext));

        //设置视频路径
        //viewHolder.myVideo.setVideoURI(uri2);

        //播放完成回调
        //viewHolder.myVideo.setOnCompletionListener( new MyPlayerOnCompletionListener());
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myName;
        public VideoView myVideo;
        public Button myButton; // 对应播放的按钮
        public ViewHolder(View itemView) {
            super(itemView);
            myName =  itemView.findViewById(R.id.textView);
            myVideo = itemView.findViewById(R.id.videoView);
            myButton = itemView.findViewById(R.id.imageButton);
        }
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(mContext, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
