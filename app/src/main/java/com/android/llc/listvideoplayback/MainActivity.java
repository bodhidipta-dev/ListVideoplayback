package com.android.llc.listvideoplayback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.llc.listvideoplayback.ui.MediaPlayerWrapper;
import com.android.llc.listvideoplayback.ui.PlayerItemChangeListener;
import com.android.llc.listvideoplayback.ui.SingleVideoPlayerManager;
import com.android.llc.listvideoplayback.ui.VideoPlayerManager;
import com.android.llc.listvideoplayback.ui.meta.MetaData;

public class MainActivity extends AppCompatActivity {
    RecyclerView video_list;
    LinearLayoutManager linearLayoutManager;
    private VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData metaData) {

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video_list = (RecyclerView) findViewById(R.id.video_list);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        video_list.setLayoutManager(linearLayoutManager);
        video_list.setAdapter(new RecyclerAdapterVideo(MainActivity.this));

        video_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    final RecyclerView.ViewHolder middle = recyclerView.findContainingViewHolder(recyclerView.findChildViewUnder(recyclerView.getWidth() / 2, (recyclerView.computeVerticalScrollExtent() / 2)));
                    if (middle instanceof RecyclerAdapterVideo.ViewHolderVideo) {
                        Log.i("@@Recycler", " Found ViewHolderVideo type at position :: " + recyclerView.getChildLayoutPosition(recyclerView.findChildViewUnder(recyclerView.getWidth() / 2, (recyclerView.computeVerticalScrollExtent() / 2))));
                    }
                    ((RecyclerAdapterVideo.ViewHolderVideo) middle).mPlayer.addMediaPlayerListener(new MediaPlayerWrapper.MainThreadMediaPlayerListener() {
                        @Override
                        public void onVideoSizeChangedMainThread(int width, int height) {

                        }

                        @Override
                        public void onVideoPreparedMainThread() {
                        }

                        @Override
                        public void onVideoCompletionMainThread() {

                        }

                        @Override
                        public void onErrorMainThread(int what, int extra) {

                        }

                        @Override
                        public void onBufferingUpdateMainThread(int percent) {

                        }

                        @Override
                        public void onVideoStoppedMainThread() {

                        }
                    });
                    mVideoPlayerManager.playNewVideo(null, ((RecyclerAdapterVideo.ViewHolderVideo) middle).mPlayer, "http://www.sample-videos.com/video/mp4/360/big_buck_bunny_360p_20mb.mp4");

                } else {
                    mVideoPlayerManager.stopAnyPlayback();

                }
            }
        });
    }
}
