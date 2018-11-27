/*
 * chsi
 * Created on 2018-11-27
 */
package com.zyl.concurrent;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread implements Runnable {

        @Override
        public void run() {
            while ( !ready ) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
//        System.out.println(ready);
//        System.out.println(number);
        new Thread(new ReaderThread()).start();
        number = 20;
        ready = true;
    }
}