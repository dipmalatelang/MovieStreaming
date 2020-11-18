package com.netflix.app.home.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.session.PlaybackState;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;


import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

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
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.DefaultTimeBar;

import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import com.google.android.exoplayer2.util.Util;
import com.netflix.app.R;
import com.netflix.app.databinding.ActivityPlayMovieBinding;
import com.netflix.app.utlis.SharedPrefs;

import java.util.List;


public class PlayMovieActivity extends AppCompatActivity {
    public static final String URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4";
    public static final String STREAM_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private SimpleExoPlayer simpleExoPlayer;
    private ActivityPlayMovieBinding binding;
    private SeekBar seekbar;
    private Button next, btn_lock, btn_subtitles;
    private LinearLayout linearlayout_medium, linear_bottom;
    private DefaultTimeBar exo_progress;
    private ImageButton btn_unlock, btn_back;
    long mLastPosition;
    long lastvideo;

    public static String LAST_VIDEO_PLAYED = "lastposition";
    public static String LAST_MINUTE_VIDEO_PLAYED = "lastminuteposition";


    @SuppressLint("SourceLockedOrientationActivity")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_movie);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        initView();
        initBrigthnessSeekbar();

        getWindow().setFlags(1024, 1024);


//        simpleExoPlayer.setTextOutput(new ComponentListener());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(PlayMovieActivity.this, "next ", Toast.LENGTH_SHORT).show();
                Uri videoUrl = Uri.parse(URL);
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
                seekbar.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
                linearlayout_medium.setVisibility(View.GONE);
                btn_back.setVisibility(View.GONE);
                binding.playerView.setKeepScreenOn(true);
                linear_bottom.setVisibility(View.GONE);
                exo_progress.setVisibility(View.GONE);
                btn_unlock.setVisibility(View.VISIBLE);
                btn_lock.setVisibility(View.GONE);


            }
        });
        btn_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seekbar.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                linearlayout_medium.setVisibility(View.VISIBLE);
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
        seekbar = binding.playerView.findViewById(R.id.seekbar);
        next = findViewById(R.id.next);
        btn_lock = findViewById(R.id.btn_lock);
        btn_unlock = findViewById(R.id.btn_unlock);
        btn_subtitles = findViewById(R.id.btn_subtitles);
        linearlayout_medium = findViewById(R.id.linearlayout_medium);
        linear_bottom = findViewById(R.id.linear_bottom);
        exo_progress = findViewById(R.id.exo_progress);
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

    @Override
    protected void onStart() {
        super.onStart();


        if (simpleExoPlayer == null) {
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
            binding.playerView.setPlayer(simpleExoPlayer);
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "exo-demo"));
            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(STREAM_URL));
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
            simpleExoPlayer.addListener(new Player.EventListener() {
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
                    simpleExoPlayer.retry();
                }

                public void onPositionDiscontinuity(int reason) {
                }

                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                }

                public void onSeekProcessed() {


                }

            });
        }

        Toast.makeText(this, "video playing", Toast.LENGTH_SHORT).show();

    }

    public void onPause() {
        super.onPause();
        //stop video when ready
        simpleExoPlayer.setPlayWhenReady(false);
        //Get playback state
        simpleExoPlayer.getPlaybackState();
    }


    public void onRestart() {
        super.onRestart();
        // play video when ready
        simpleExoPlayer.setPlayWhenReady(true);
        //get playback state
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        simpleExoPlayer.seekTo(SharedPrefs.getInstance().getlastPositionVideo(LAST_MINUTE_VIDEO_PLAYED,lastvideo));
        simpleExoPlayer.setPlayWhenReady(true);
        Log.d("TAG", "onResume: " + SharedPrefs.getInstance().getlastPositionVideo(LAST_MINUTE_VIDEO_PLAYED,lastvideo));

    }

    @Override
    protected void onStop() {
        super.onStop();
        simpleExoPlayer.setPlayWhenReady(true);
        mLastPosition = simpleExoPlayer.getCurrentPosition();
        Log.d("TAG", "onStop: " + mLastPosition);
        SharedPrefs.getInstance().setlastPositionVideo(LAST_MINUTE_VIDEO_PLAYED, mLastPosition);
        simpleExoPlayer.setPlayWhenReady(false);


    }


}
