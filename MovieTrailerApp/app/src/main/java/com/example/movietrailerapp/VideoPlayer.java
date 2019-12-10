package com.example.movietrailerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlayer extends YouTubeBaseActivity {

    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videolayout);
        btnPlay = findViewById(R.id.youtubeplaybutton);
        mYouTubePlayerView = findViewById(R.id.videoview);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                String videolink= getIntent().getStringExtra("vlink");
                youTubePlayer.loadVideo(videolink);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYouTubePlayerView.initialize(PlayerConfig.getApiKey(),mOnInitializedListener);
            }
        });
    }
}
