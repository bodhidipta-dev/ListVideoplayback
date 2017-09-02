package com.android.llc.listvideoplayback.ui.player_messages;


import com.android.llc.listvideoplayback.ui.PlayerMessageState;
import com.android.llc.listvideoplayback.ui.VideoPlayerManagerCallback;
import com.android.llc.listvideoplayback.ui.VideoPlayerView;

/**
 * This is generic interface for PlayerMessage
 */
public abstract class PlayerMessage implements Message {

    private static final String TAG = PlayerMessage.class.getSimpleName();
    private final VideoPlayerView mCurrentPlayer;
    private final VideoPlayerManagerCallback mCallback;

    public PlayerMessage(VideoPlayerView currentPlayer, VideoPlayerManagerCallback callback) {
        mCurrentPlayer = currentPlayer;
        mCallback = callback;
    }

    protected final PlayerMessageState getCurrentState(){
        return mCallback.getCurrentPlayerState();
    }

    @Override
    public final void polledFromQueue() {
        mCallback.setVideoPlayerState(mCurrentPlayer, stateBefore());
    }

    @Override
    public final void messageFinished() {
        mCallback.setVideoPlayerState(mCurrentPlayer, stateAfter());
    }

    public final void runMessage(){
        performAction(mCurrentPlayer);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    protected abstract void performAction(VideoPlayerView currentPlayer);
    protected abstract PlayerMessageState stateBefore();
    protected abstract PlayerMessageState stateAfter();

}
