/*
 * chsi
 * Created on 2018-11-23
 */
package com.zyl.concurrent;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class DeadLock {
    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock.new DeadLockThread(deadLock.obj1, deadLock.obj2)).start();
        new Thread(deadLock.new DeadLockThread(deadLock.obj2, deadLock.obj1)).start();
    }

    class DeadLockThread implements Runnable {
        private final Object lock1;
        private final Object lock2;

        public DeadLockThread(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized ( lock1 ) {
                System.out.println(Thread.currentThread().getName() + " get the first lock！");
                try {
                    Thread.sleep(3000);
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }

                synchronized ( lock2 ) {
                    System.out.println(Thread.currentThread().getName() + " I am here！");
                }
            }
        }
    }
}