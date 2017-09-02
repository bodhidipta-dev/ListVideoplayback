package com.android.llc.listvideoplayback.ui;

import android.util.Pair;

import com.android.llc.listvideoplayback.Logger;


public class ReadyForPlaybackIndicator {

    private static final String TAG = ReadyForPlaybackIndicator.class.getSimpleName();


    private Pair<Integer, Integer> mVideoSize;
    private boolean mSurfaceTextureAvailable;
    private boolean mFailedToPrepareUiForPlayback = false;

    public boolean isVideoSizeAvailable() {
        boolean isVideoSizeAvailable = mVideoSize.first != null && mVideoSize.second != null;
         Logger.showLog(TAG,  "isVideoSizeAvailable " + isVideoSizeAvailable);
        return isVideoSizeAvailable;
    }

    public boolean isSurfaceTextureAvailable() {
         Logger.showLog(TAG,  "isSurfaceTextureAvailable " + mSurfaceTextureAvailable);
        return mSurfaceTextureAvailable;
    }

    public boolean isReadyForPlayback() {
        boolean isReadyForPlayback = isVideoSizeAvailable() && isSurfaceTextureAvailable();
         Logger.showLog(TAG,  "isReadyForPlayback " + isReadyForPlayback);
        return isReadyForPlayback;
    }

    public void setSurfaceTextureAvailable(boolean available) {
        mSurfaceTextureAvailable = available;
    }

    public void setVideoSize(Integer videoHeight, Integer videoWidth) {
        mVideoSize = new Pair<>(videoHeight, videoWidth);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + isReadyForPlayback();
    }

    public void setFailedToPrepareUiForPlayback(boolean failed) {
        mFailedToPrepareUiForPlayback = failed;
    }

    public boolean isFailedToPrepareUiForPlayback() {
        return mFailedToPrepareUiForPlayback;
    }
}
