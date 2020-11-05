package com.netflix.app.home.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.navigation.NavigationView;
import com.netflix.app.R;

public class PlayMovieActivity extends AppCompatActivity {
    public static final String URL = "https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4";
    ImageView btn_fullscreen;
    boolean flag = false;
    PlayerView playerView;
    ProgressBar progressBar;
    private SimpleExoPlayer simpleExoPlayer;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_movie);
        this.playerView = findViewById(R.id.player_view);
        this.progressBar = findViewById(R.id.progressBar);
        this.btn_fullscreen = this.playerView.findViewById(R.id.btn_fullscreen);
        getWindow().setFlags(1024, 1024);
        Uri videoUrl = Uri.parse(URL);
        this.simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())), new DefaultLoadControl());
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource(videoUrl, new DefaultHttpDataSourceFactory("exoplayer_video"), new DefaultExtractorsFactory(), null, null);
        this.playerView.setPlayer(this.simpleExoPlayer);
        this.playerView.setKeepScreenOn(true);
        this.simpleExoPlayer.prepare(extractorMediaSource);
        this.simpleExoPlayer.setPlayWhenReady(true);
        this.simpleExoPlayer.addListener(new Player.EventListener() {
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
            }

            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            }

            public void onLoadingChanged(boolean isLoading) {
            }

            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == 2) {
                    PlayMovieActivity.this.progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState == 3) {
                    PlayMovieActivity.this.progressBar.setVisibility(View.GONE);
                }
            }

            public void onRepeatModeChanged(int repeatMode) {
            }

            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
            }

            public void onPlayerError(ExoPlaybackException error) {
            }

            public void onPositionDiscontinuity(int reason) {
            }

            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            }

            public void onSeekProcessed() {
            }
        });
        this.btn_fullscreen.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SourceLockedOrientationActivity")
            public void onClick(View v) {
                if (PlayMovieActivity.this.flag) {
                    PlayMovieActivity.this.btn_fullscreen.setImageDrawable(PlayMovieActivity.this.getResources().getDrawable(R.drawable.ic_fullscreen));
                    PlayMovieActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    PlayMovieActivity.this.flag = false;
                    return;
                }
                PlayMovieActivity.this.btn_fullscreen.setImageDrawable(PlayMovieActivity.this.getResources().getDrawable(R.drawable.ic_baseline_fullscreen_exit_24));
                PlayMovieActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                PlayMovieActivity.this.flag = true;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.simpleExoPlayer.setPlayWhenReady(false);
        this.simpleExoPlayer.getPlaybackState();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        this.simpleExoPlayer.setPlayWhenReady(true);
        this.simpleExoPlayer.getPlaybackState();
    }
}
