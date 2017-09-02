package com.android.llc.listvideoplayback.ui;




import com.android.llc.listvideoplayback.ui.player_messages.Message;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessagesHandlerThread {

    private static final String TAG = MessagesHandlerThread.class.getSimpleName();

    private final Queue<Message> mPlayerMessagesQueue = new ConcurrentLinkedQueue<>();
    private final PlayerQueueLock mQueueLock = new PlayerQueueLock();
    private final Executor mQueueProcessingThread = Executors.newSingleThreadExecutor();

    private AtomicBoolean mTerminated = new AtomicBoolean(false); // TODO: use it
    private Message mLastMessage;

    public MessagesHandlerThread() {
        mQueueProcessingThread.execute(new Runnable() {
            @Override
            public void run() {

                do {

                    mQueueLock.lock(TAG);

                    if (mPlayerMessagesQueue.isEmpty()) {
                        try {

                            mQueueLock.wait(TAG);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            throw new RuntimeException("InterruptedException");
                        }
                    }
                    mLastMessage = mPlayerMessagesQueue.poll();

                    mLastMessage.polledFromQueue();
                    mQueueLock.unlock(TAG);

                    mLastMessage.runMessage();

                    mQueueLock.lock(TAG);
                    mLastMessage.messageFinished();
                    mQueueLock.unlock(TAG);

                } while (!mTerminated.get());

            }
        });
    }

    /**
     * Use it if you need to add a single message
     */
    public void addMessage(Message message){

        mQueueLock.lock(TAG);

        mPlayerMessagesQueue.add(message);
        mQueueLock.notify(TAG);

        mQueueLock.unlock(TAG);
    }

    /**
     * Use it if you need to add a multiple messages
     */
    public void addMessages(List<? extends Message> messages) {
        mQueueLock.lock(TAG);

        mPlayerMessagesQueue.addAll(messages);
        mQueueLock.notify(TAG);

        mQueueLock.unlock(TAG);
    }

    public void pauseQueueProcessing(String outer){
        mQueueLock.lock(outer);
    }

    public void resumeQueueProcessing(String outer){
        mQueueLock.unlock(outer);
    }

    public void clearAllPendingMessages(String outer) {

        if(mQueueLock.isLocked(outer)){
            mPlayerMessagesQueue.clear();
        } else {
            throw new RuntimeException("cannot perform action, you are not holding a lock");
        }
    }

    public void terminate(){
        mTerminated.set(true);
    }
}
