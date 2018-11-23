/*
 * chsi
 * Created on 2018-11-23
 */
package com.zyl.java.thread;

/**
 * 线程实例的方法join()方法可以使得一个线程在另一个线程结束后再执行。如果join()方法在一个线程实例上调用，
 * 当前运行着的线程将阻塞直到这个线程实例完成了执行,join是Thread的非静态方法
 *
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Join join = new Join();
        Thread t1 = new Thread(join.new JoinThread(6000));
        Thread t2 = new Thread(join.new JoinThread(2000));
        t1.start();
        t2.start();
        t1.join(1000);
        System.out.println("run main after join1");
        t2.join();
        System.out.println("run main after join2");
        new Thread(join.new JoinThread(100)).start();
    }

    class JoinThread implements Runnable {
        private int sleepTime;

        public JoinThread(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " run");
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + " done ");
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }

        }
    }
}