package com.android.llc.listvideoplayback.ui.player_messages;

import android.media.MediaPlayer;

import com.android.llc.listvideoplayback.ui.MediaPlayerWrapper;
import com.android.llc.listvideoplayback.ui.PlayerMessageState;
import com.android.llc.listvideoplayback.ui.VideoPlayerManagerCallback;
import com.android.llc.listvideoplayback.ui.VideoPlayerView;

public class Prepare extends PlayerMessage{

    private static final String TAG = Prepare.class.getSimpleName();

    private PlayerMessageState mResultPlayerMessageState;

    public Prepare(VideoPlayerView videoPlayerView, VideoPlayerManagerCallback callback) {
        super(videoPlayerView, callback);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {

        currentPlayer.prepare();

        MediaPlayerWrapper.State resultOfPrepare = currentPlayer.getCurrentState();

        switch (resultOfPrepare){
            case IDLE:
            case INITIALIZED:
            case PREPARING:
            case STARTED:
            case PAUSED:
            case STOPPED:
            case PLAYBACK_COMPLETED:
            case END:
                throw new RuntimeException("unhandled state " + resultOfPrepare);

            case PREPARED:
                mResultPlayerMessageState = PlayerMessageState.PREPARED;
                break;

            case ERROR:
                mResultPlayerMessageState = PlayerMessageState.ERROR;
                break;
        }
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.PREPARING;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return mResultPlayerMessageState;
    }
}
