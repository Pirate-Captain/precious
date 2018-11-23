/*
 * chsi
 * Created on 2018-11-23
 */
package com.zyl.java.thread;

/**
 * yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；
 * 但是，并不能保证在当前线程调用yield()之后，
 * 其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
 * yield 不会释放锁住的资源，yield是Thread方法
 *
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Yield {

    public static void main(String[] args) {
        Yield yield = new Yield();
        new Thread(yield.new YieldThread()).start();
        new Thread(yield.new YieldThread()).start();
    }

    class YieldThread implements Runnable {

        @SuppressWarnings("InfiniteLoopStatement")
        @Override
        public void run() {
            while ( true ) {
                System.out.println(Thread.currentThread().getName());
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + " yield 后继续执行");
                try {
                    Thread.sleep(1000);
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}