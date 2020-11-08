package com.netflix.app.home.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.ContentResolver;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.session.PlaybackState;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;


import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import com.netflix.app.R;
import com.netflix.app.databinding.ActivityPlayMovieBinding;

import java.util.List;

public class PlayMovieActivity extends AppCompatActivity {
    public static final String URL = "https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4";
    public static final String STREAM_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    ImageView btn_fullscreen;

    boolean flag = false;
    private SimpleExoPlayer simpleExoPlayer;
    ActivityPlayMovieBinding binding;
    SeekBar seekbar;
    SubtitleView subtitleView;
    Button next;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_movie);

        this.btn_fullscreen = binding.playerView.findViewById(R.id.btn_fullscreen);
        this.seekbar = binding.playerView.findViewById(R.id.seekbar);
        next = findViewById(R.id.next);
        subtitleView=findViewById(com.google.android.exoplayer2.R.id.exo_subtitles);

        int cBrightness = Settings.System.getInt(getContentResolver()
                , Settings.System.SCREEN_BRIGHTNESS, 0);
        seekbar.setProgress(cBrightness);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Context context = getApplicationContext();
                boolean canWrite = Settings.System.canWrite(context);
                if (canWrite) {
                    int sBrightness = progress * 255 / 255;
                    Settings.System.putInt(context.getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS_MODE,
                            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                    Settings.System.putInt(context.getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS, sBrightness);
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    startActivity(intent);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        getWindow().setFlags(1024, 1024);
        Uri videoUrl = Uri.parse(URL);
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())), new DefaultLoadControl());
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource(videoUrl, new DefaultHttpDataSourceFactory("exoplayer_video"), new DefaultExtractorsFactory(), null, null);
        binding.playerView.setPlayer(this.simpleExoPlayer);
        binding.playerView.setKeepScreenOn(true);
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
                    binding.progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState == 3) {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                }else if(playbackState == PlaybackState.STATE_SKIPPING_TO_NEXT)
                {
                    Toast.makeText(PlayMovieActivity.this, "next", Toast.LENGTH_SHORT).show();

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
        btn_fullscreen.setOnClickListener(new View.OnClickListener() {
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
                subtitleView.setVisibility(View.VISIBLE);

                PlayMovieActivity.this.flag = true;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Toast.makeText(PlayMovieActivity.this, "next ", Toast.LENGTH_SHORT).show();
                    Uri videoUrl = Uri.parse(STREAM_URL);
                    ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource(videoUrl, new DefaultHttpDataSourceFactory("exoplayer_video"), new DefaultExtractorsFactory(), null, null);
                    binding.playerView.setPlayer(PlayMovieActivity.this.simpleExoPlayer);
                    binding.playerView.setKeepScreenOn(true);
                    PlayMovieActivity.this.simpleExoPlayer.prepare(extractorMediaSource);
                    PlayMovieActivity.this.simpleExoPlayer.setPlayWhenReady(true);



            }
        });


        simpleExoPlayer.setTextOutput(new ComponentListener());


    }
    public class ComponentListener implements TextRenderer.Output{
        @Override
        public void onCues(List<Cue> cues) {
            if (subtitleView != null) {
                subtitleView.onCues(cues);
            }   }
        }


    public void onPause() {
        super.onPause();
        this.simpleExoPlayer.setPlayWhenReady(false);
        this.simpleExoPlayer.getPlaybackState();
    }


    public void onRestart() {
        super.onRestart();
        this.simpleExoPlayer.setPlayWhenReady(true);
        this.simpleExoPlayer.getPlaybackState();
    }
}
