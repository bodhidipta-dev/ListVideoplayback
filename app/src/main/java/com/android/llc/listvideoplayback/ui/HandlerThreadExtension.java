package com.android.llc.listvideoplayback.ui;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import com.android.llc.listvideoplayback.Logger;

public class HandlerThreadExtension extends HandlerThread {


    private static final String TAG = HandlerThreadExtension.class.getSimpleName();

    private Handler mHandler;
    private final Object mStart = new Object();

    /**
     * @param name
     * @param setupExceptionHandler
     */
    public HandlerThreadExtension(String name, boolean setupExceptionHandler){
        super(name);
        if(setupExceptionHandler){
            setUncaughtExceptionHandler(new UncaughtExceptionHandler(){
                @Override
                public void uncaughtException(Thread thread, Throwable ex){

                    Logger.showLog(TAG, "uncaughtException, " + ex.getMessage());
                    ex.printStackTrace();
                    System.exit(0);
                }
            });
        }
    }

    @Override
    protected void onLooperPrepared(){
        Logger.showLog(TAG, "onLooperPrepared " + this);

        mHandler = new Handler();
        mHandler.post(new Runnable(){
            @Override
            public void run(){
                synchronized(mStart){
                    mStart.notifyAll();
                }
            }
        });
    }

    public void post(Runnable r){

        boolean successfullyAddedToQueue = mHandler.post(r);

        Logger.showLog(TAG, "post, successfullyAddedToQueue "+successfullyAddedToQueue);

    }

    public void postAtFrontOfQueue(Runnable r){
        mHandler.postAtFrontOfQueue(r);
    }

    public void startThread(){
        Logger.showLog(TAG, ">> startThread");

        synchronized(mStart){
            start();
            try{
                mStart.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        Logger.showLog(TAG, "<< startThread");

    }

    public void postQuit(){
        mHandler.post(new Runnable(){
            @Override
            public void run(){
                Logger.showLog(TAG, "postQuit, run");
                Looper.myLooper().quit();
            }
        });
    }

    public void remove(Runnable runnable){
        mHandler.removeCallbacks(runnable);
    }
}

