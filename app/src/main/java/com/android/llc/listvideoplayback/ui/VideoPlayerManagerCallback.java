package com.android.llc.listvideoplayback.ui;


import com.android.llc.listvideoplayback.ui.meta.MetaData;

public interface VideoPlayerManagerCallback {

    void setCurrentItem(MetaData currentItemMetaData, VideoPlayerView newPlayerView);

    void setVideoPlayerState(VideoPlayerView videoPlayerView, PlayerMessageState playerMessageState);

    PlayerMessageState getCurrentPlayerState();
}
