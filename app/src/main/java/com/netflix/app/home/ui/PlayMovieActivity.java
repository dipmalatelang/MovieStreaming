package com.netflix.app.home.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.ContentResolver;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.session.PlaybackState;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;


import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
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
import com.google.android.exoplayer2.ui.DefaultTimeBar;

import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import com.netflix.app.R;
import com.netflix.app.databinding.ActivityPlayMovieBinding;

import java.util.List;


public class PlayMovieActivity extends AppCompatActivity {
    public static final String URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4";
    public static final String STREAM_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private ImageView btn_fullscreen;

    boolean flag = false;
    private SimpleExoPlayer simpleExoPlayer;
    private ActivityPlayMovieBinding binding;
    private  SeekBar seekbar;
    private Button next, btn_lock, btn_subtitles, exo_episode;
    private TextView Tv_subtitle;
    private LinearLayout linearlayout_medium, linear_bottom;
    private DefaultTimeBar exo_progress;
    private ImageButton btn_unlock, btn_back;



    @SuppressLint("SourceLockedOrientationActivity")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_movie);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initView();
        initBrigthnessSeekbar();


        getWindow().setFlags(1024, 1024);
        Uri videoUrl = Uri.parse(URL);
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector
                (new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())), new DefaultLoadControl());
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource(videoUrl,
                new DefaultHttpDataSourceFactory("exoplayer_video"), new DefaultExtractorsFactory(),
                null, null);


        binding.playerView.setPlayer(this.simpleExoPlayer);
        binding.playerView.setControllerAutoShow(false);
        binding.playerView.setControllerShowTimeoutMs(0);
        binding.playerView.setUseArtwork(false);
        simpleExoPlayer.prepare(extractorMediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.setTextOutput(new ComponentListener());

        binding.playerView.setKeepScreenOn(true);
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
                } else if (playbackState == PlaybackState.STATE_SKIPPING_TO_NEXT) {
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


//        btn_fullscreen.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SourceLockedOrientationActivity")
//            public void onClick(View v) {
//                if (PlayMovieActivity.this.flag) {
//                    PlayMovieActivity.this.btn_fullscreen.setImageDrawable(PlayMovieActivity.this.getResources().getDrawable(R.drawable.ic_fullscreen));
//                    PlayMovieActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    PlayMovieActivity.this.flag = false;
//                    return;
//                }
//                PlayMovieActivity.this.btn_fullscreen.setImageDrawable(PlayMovieActivity.this.getResources().getDrawable(R.drawable.ic_baseline_fullscreen_exit_24));
//                PlayMovieActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                PlayMovieActivity.this.flag = true;
//            }
//        });
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
        btn_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_subtitles.setVisibility(View.GONE);
                seekbar.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
                linearlayout_medium.setVisibility(View.GONE);
                exo_episode.setVisibility(View.GONE);
                btn_back.setVisibility(View.GONE);
                binding.playerView.setKeepScreenOn(true);
                Tv_subtitle.setVisibility(View.GONE);
                linear_bottom.setVisibility(View.GONE);
                exo_progress.setVisibility(View.GONE);
                btn_unlock.setVisibility(View.VISIBLE);
                btn_lock.setVisibility(View.GONE);


            }
        });
        btn_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_subtitles.setVisibility(View.VISIBLE);
                seekbar.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                linearlayout_medium.setVisibility(View.VISIBLE);
                exo_episode.setVisibility(View.VISIBLE);
                btn_back.setVisibility(View.VISIBLE);
                btn_unlock.setVisibility(View.GONE);
                btn_lock.setVisibility(View.VISIBLE);


            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayMovieActivity.this, MovieDetailActivity.class);
                startActivity(intent);
            }
        });
        btn_subtitles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.playerView.getSubtitleView().setVisibility(View.VISIBLE);
                binding.subtitles.setVisibility(View.VISIBLE);
                Toast.makeText(PlayMovieActivity.this, "Click Subtitle view", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initBrigthnessSeekbar() {
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


    }

    private void initView() {
        this.btn_fullscreen = binding.playerView.findViewById(R.id.btn_fullscreen);
        this.seekbar = binding.playerView.findViewById(R.id.seekbar);

        next = findViewById(R.id.next);
        btn_lock = findViewById(R.id.btn_lock);
        btn_unlock = findViewById(R.id.btn_unlock);
        btn_subtitles = findViewById(R.id.btn_subtitles);
        Tv_subtitle = findViewById(R.id.Tv_subtitle);
        linearlayout_medium = findViewById(R.id.linearlayout_medium);
        linear_bottom = findViewById(R.id.linear_bottom);
        exo_progress = findViewById(R.id.exo_progress);
        exo_episode = findViewById(R.id.exo_episode);
        btn_back = findViewById(R.id.btn_back);
    }

    public class ComponentListener implements TextRenderer.Output {
        @Override
        public void onCues(List<Cue> cues) {
            if (binding.subtitles != null) {
                binding.subtitles.onCues(cues);
            }
        }
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
