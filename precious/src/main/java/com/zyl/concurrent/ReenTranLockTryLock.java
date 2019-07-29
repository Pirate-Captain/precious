/*
 * chsi
 * Created on 2018-11-23
 */
package com.zyl.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ReenTranLockTryLock {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReenTranLockTryLock lockTryLock = new ReenTranLockTryLock();
        new Thread(lockTryLock.new ReenTranLockTryLockThread()).start();
        new Thread(lockTryLock.new ReenTranLockTryLockThread()).start();
    }

    class ReenTranLockTryLockThread implements Runnable {

        @Override
        public void run() {
            print();
        }
    }

    private void print() {
        if ( !lock.tryLock() ) {
            System.out.println("获取锁失败");
            return;
        }
        System.out.println(Thread.currentThread().getName() + "获取锁");
        try {
            Thread.sleep(3000);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        try {
            System.out.println(Thread.currentThread().getName() + " say hello！");
        } finally {
            lock.unlock();
        }
    }
}