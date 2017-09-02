package com.android.llc.listvideoplayback.ui.player_messages;


import com.android.llc.listvideoplayback.ui.PlayerMessageState;
import com.android.llc.listvideoplayback.ui.VideoPlayerManagerCallback;
import com.android.llc.listvideoplayback.ui.VideoPlayerView;

/**
 * This PlayerMessage calls {@link MediaPlayer#reset()} on the instance that is used inside {@link VideoPlayerView}
 */
public class Reset extends PlayerMessage {
    public Reset(VideoPlayerView videoPlayerView, VideoPlayerManagerCallback callback) {
        super(videoPlayerView, callback);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.reset();
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.RESETTING;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.RESET;
    }
}
