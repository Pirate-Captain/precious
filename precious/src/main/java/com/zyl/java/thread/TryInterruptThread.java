/*
 * chsi
 * Created on 2018-11-19
 */
package com.zyl.java.thread;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TryInterruptThread {
    public static void main(String[] args) throws InterruptedException {
        TryInterruptThread tryInterruptThread = new TryInterruptThread();
        Thread myThread = new Thread(tryInterruptThread.new MyThread());
        myThread.start();
        myThread.interrupt();
        System.out.println(myThread.isInterrupted());
        System.out.println(myThread.getState());
    }
    public class MyThread implements Runnable {

        @Override
        public void run() {
            System.out.println("before business");
            try {
                Thread.sleep(300000);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
//                Thread.currentThread().interrupt();
            }
            System.out.println("after business");
        }
    }
}