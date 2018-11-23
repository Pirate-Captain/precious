/*
 * chsi
 * Created on 2018-11-23
 */
package com.zyl.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁将对一个资源（比如文件）的访问分成了2个锁，一个读锁和一个写锁。
 * 正因为有了读写锁，才使得多个线程之间的读操作不会发生冲突。
 * 不过要注意的是，如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。
 * 如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁。
 *
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ReenTranLockReadWrite {
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReenTranLockReadWrite lockReadWrite = new ReenTranLockReadWrite();
        for ( int index = 1; index <= 10; index++ ) {
            if ( index % 4 == 0 ) {
                new Thread(lockReadWrite.new ReenTranLockReadWriteThread(false)).start();
            } else {
                new Thread(lockReadWrite.new ReenTranLockReadWriteThread(true)).start();
            }
        }
    }

    class ReenTranLockReadWriteThread implements Runnable {
        private boolean read;

        public ReenTranLockReadWriteThread(boolean read) {
            this.read = read;
        }

        @Override
        public void run() {
            if ( read ) {
                read();
            } else {
                write();
            }
        }
    }

    private void read() {
        System.out.println(Thread.currentThread().getName() + " ready read");
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read");
            try {
                Thread.sleep(3000);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    private void write() {
        System.out.println(Thread.currentThread().getName() + " ready write ");
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " write");
            try {
                Thread.sleep(3000);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}