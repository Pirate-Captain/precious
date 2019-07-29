/*
 * chsi
 * Created on 2018-11-22
 */
package com.zyl.java.thread.cosumerandproducer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ProducerAndConsumerLock {
    private Queue<Integer> queue = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 1, TimeUnit.HOURS, new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory());
        ProducerAndConsumerLock notify = new ProducerAndConsumerLock();
        for ( int index = 0; index < 10; index++ ) {
            if ( index % 4 == 0 ) {
                executorService.submit(notify.new Producer("producer" + index));
            } else {
                executorService.submit(notify.new Consumer("consumer" + index));
            }
        }
    }

    class Producer implements Runnable {
        private String name;

        public Producer(String name) {
            this.name = name;
        }

        @SuppressWarnings("InfiniteLoopStatement")
        @Override
        public void run() {
            int queuMaxSize = 5;
            while ( true ) {
                try {
                    Thread.sleep(1000);
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    while ( queue.size() == queuMaxSize ) {
                        try {
                            notFull.await();
                        } catch ( InterruptedException e ) {
                            e.printStackTrace();
                        }
                    }
                    int num = Double.valueOf(Math.random() * 1000).intValue();
                    System.out.println(this.name + "烤了只全羊:" + num);
                    queue.offer(num);
                    notEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @SuppressWarnings("InfiniteLoopStatement")
        @Override
        public void run() {
            while ( true ) {
                try {
                    Thread.sleep(1000);
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    while ( queue.isEmpty() ) {
                        try {
                            notEmpty.await();
                        } catch ( InterruptedException e ) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(this.name + "吃了一只羊：" + queue.poll());
                    notFull.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}