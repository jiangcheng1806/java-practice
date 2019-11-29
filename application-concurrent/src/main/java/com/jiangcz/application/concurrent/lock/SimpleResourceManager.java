package com.jiangcz.application.concurrent.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 类名称：SimpleResourceManager<br>
 * 类描述：对有限个共享资源的访问<br>
 * 创建时间：2018年08月01日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class SimpleResourceManager {

    private final InnerSynchronizer synchronizer;

    private static class InnerSynchronizer extends AbstractQueuedSynchronizer {
        InnerSynchronizer(int numOfResources) {
            setState(numOfResources);
        }

        @Override
        protected int tryAcquireShared(int acquires){
            for (;;){
                int available = getState();
                int remain = available - acquires;
                if (remain < 0 || compareAndSetState(available,remain)){
                    return remain;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            for (;;){
                int available = getState();
                int next = available + releases;
                if (compareAndSetState(available,next)){
                    return true;
                }
            }
        }
    }

    public SimpleResourceManager(int numOfResources) {
        synchronizer = new InnerSynchronizer(numOfResources);
    }

    public void acquire() throws InterruptedException {
        synchronizer.acquireSharedInterruptibly(1);
    }

    public void release(){
        synchronizer.releaseShared(1);
    }
}
