package com.android.llc.listvideoplayback.ui;


import com.android.llc.listvideoplayback.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PlayerQueueLock {

    private static final String TAG = PlayerQueueLock.class.getSimpleName();
    private static final boolean SHOW_LOGS = false;
    private final ReentrantLock mQueueLock = new ReentrantLock();
    private final Condition mProcessQueueCondition = mQueueLock.newCondition();

    public void lock(String owner){
         Logger.showLog(TAG,  ">> lock, owner [" + owner + "]");
        mQueueLock.lock();
         Logger.showLog(TAG,  "<< lock, owner [" + owner + "]");
    }

    public void unlock(String owner){
         Logger.showLog(TAG,  ">> unlock, owner [" + owner + "]");
        mQueueLock.unlock();
         Logger.showLog(TAG,  "<< unlock, owner [" + owner + "]");
    }

    public boolean isLocked(String owner){
        boolean isLocked = mQueueLock.isLocked();
         Logger.showLog(TAG,  "isLocked, owner [" + owner + "]");
        return isLocked;
    }

    public void wait(String owner) throws InterruptedException {
         Logger.showLog(TAG,  ">> wait, owner [" + owner + "]");
        mProcessQueueCondition.await();
         Logger.showLog(TAG,  "<< wait, owner [" + owner + "]");
    }

    public void notify(String owner) {
         Logger.showLog(TAG,  ">> notify, owner [" + owner + "]");
        mProcessQueueCondition.signal();
         Logger.showLog(TAG,  "<< notify, owner [" + owner + "]");
    }
}
