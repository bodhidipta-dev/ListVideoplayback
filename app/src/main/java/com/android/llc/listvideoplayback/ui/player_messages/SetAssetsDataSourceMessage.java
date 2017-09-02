package com.android.llc.listvideoplayback.ui.player_messages;


import android.content.res.AssetFileDescriptor;

import com.android.llc.listvideoplayback.ui.VideoPlayerManagerCallback;
import com.android.llc.listvideoplayback.ui.VideoPlayerView;

/**
 */
public class SetAssetsDataSourceMessage extends SetDataSourceMessage{

    private final AssetFileDescriptor mAssetFileDescriptor;

    public SetAssetsDataSourceMessage(VideoPlayerView videoPlayerView, AssetFileDescriptor assetFileDescriptor, VideoPlayerManagerCallback callback) {
        super(videoPlayerView, callback);
        mAssetFileDescriptor = assetFileDescriptor;
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.setDataSource(mAssetFileDescriptor);
    }
}
