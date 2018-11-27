/*
 * chsi
 * Created on 2018-11-27
 */
package com.zyl.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程的三个特性：原子性、可见性、有序性
 * volatile 能保证变量的可见性，但是不能保存原子性，如自增、自减操作。
 *
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class VolatileTest {
    private volatile int count = 0;

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20, 1, TimeUnit.HOURS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory());
        for ( int index = 0; index < 20; index++ ) {
            executor.submit(test.new VolatileThread());
        }
    }

    class VolatileThread implements Runnable {
        @Override
        public void run() {
            count++;
            System.out.println(count);
        }
    }
}