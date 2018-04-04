/*
 * chsi
 * Created on 2018-03-29
 */
package com.zyl.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class AtomicReferenceTest {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AtomicReference<Integer> counter = new AtomicReference<>(0);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int threadPoolSize = 50;
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("AtomicRefrenceTest-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(threadPoolSize, threadPoolSize, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), threadFactory);
        List<Callable<Integer>> executeTaskList = new ArrayList<>();
        for ( int i = 0; i < threadPoolSize; i++ ) {
            executeTaskList.add(() -> {
                int value = counter.getAndSet(atomicInteger.incrementAndGet());
                System.out.println(Thread.currentThread().getName() + ":" + value + ":" + (count++));
                return 1;
            });
        }
        List<Future<Integer>> futureList = executorService.invokeAll(executeTaskList);
        for ( Future<Integer> future : futureList ) {
            future.get();
        }
        executorService.shutdown();
        System.out.println("the final counter value : " + counter.get());
    }
}