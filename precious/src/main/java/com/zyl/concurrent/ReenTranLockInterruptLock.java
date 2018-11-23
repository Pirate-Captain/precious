/*
 * chsi
 * Created on 2018-11-23
 */
package com.zyl.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ReenTranLockInterruptLock {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReenTranLockInterruptLock lockInterruptLock = new ReenTranLockInterruptLock();
        Thread t1 = new Thread(lockInterruptLock.new ReenTranLockInterruptLockThread());
        Thread t2 = new Thread(lockInterruptLock.new ReenTranLockInterruptLockThread());
        t1.start();
        try {
            Thread.sleep(1000);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        t2.start();

        t2.interrupt();
        System.out.println("main run finished!");
    }

    class ReenTranLockInterruptLockThread implements Runnable {

        @Override
        public void run() {
            try {
                print();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
    }

    private void print() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " going to get lock!");
        lock.lockInterruptibly();
        try {
            System.out.println(Thread.currentThread().getName() + " run");
            Thread.sleep(3000);
        } finally {
            lock.unlock();
        }
    }
}