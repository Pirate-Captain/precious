/**
 * chsi
 * Created on 2017-09-28
 */
package com.zyl.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class CountDownLattchTest {
    private static int threadCount = 20;
    private final static CountDownLatch latch = new CountDownLatch(20);

    public static void main(String[] args) throws InterruptedException {
        for ( int index=0; index < threadCount; index++ ) {
            new Thread(new ShowMessageThread()).start();
            if ( index == threadCount - 1 ) {
                Thread.sleep(3000);
            }
            latch.countDown();
        }
    }

    static class ShowMessageThread implements Runnable {
        @Override
        public void run() {
            try {
                latch.await();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            System.out.println("hello guys：" + Thread.currentThread().getName());
        }
    }
}