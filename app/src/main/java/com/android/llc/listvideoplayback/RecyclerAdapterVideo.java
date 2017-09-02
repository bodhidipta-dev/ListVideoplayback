package com.android.llc.listvideoplayback;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.llc.listvideoplayback.ui.VideoPlayerView;

/**
 * Created by Bhaiya on 9/2/2017.
 */

public class RecyclerAdapterVideo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;

    public RecyclerAdapterVideo(Context mcontext) {
        this.mcontext = mcontext;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderVideo(LayoutInflater.from(mcontext).inflate(R.layout.video_view_row, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class ViewHolderVideo extends RecyclerView.ViewHolder {
        public VideoPlayerView mPlayer;

        public ViewHolderVideo(View itemView) {
            super(itemView);
            mPlayer = (VideoPlayerView) itemView.findViewById(R.id.player_view);
        }
    }
}
