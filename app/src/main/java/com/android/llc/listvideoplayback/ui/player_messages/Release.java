package com.android.llc.listvideoplayback.ui.player_messages;


import com.android.llc.listvideoplayback.ui.PlayerMessageState;
import com.android.llc.listvideoplayback.ui.VideoPlayerManagerCallback;
import com.android.llc.listvideoplayback.ui.VideoPlayerView;

/**
 * This PlayerMessage calls {@link MediaPlayer#release()} on the instance that is used inside {@link VideoPlayerView}
 */
public class Release extends PlayerMessage {

    public Release(VideoPlayerView videoPlayerView, VideoPlayerManagerCallback callback) {
        super(videoPlayerView, callback);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.release();
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.RELEASING;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.RELEASED;
    }
}
