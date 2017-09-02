package com.android.llc.listvideoplayback.ui.player_messages;

import com.android.llc.listvideoplayback.ui.VideoPlayerManagerCallback;
import com.android.llc.listvideoplayback.ui.VideoPlayerView;

public class SetUrlDataSourceMessage extends SetDataSourceMessage{

    private final String mVideoUrl;

    public SetUrlDataSourceMessage(VideoPlayerView videoPlayerView, String videoUrl, VideoPlayerManagerCallback callback) {
        super(videoPlayerView, callback);
        mVideoUrl = videoUrl;
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.setDataSource(mVideoUrl);
    }
}
