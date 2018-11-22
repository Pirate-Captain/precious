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

/**
 * 使用wait 以及 notify实现的生产者，消费者模式
 * <p>
 * wait一定要放到synchronized中的while循环中，不然会导致预想不到的问题
 *
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ProducerAndConsumerWaitNotify {
    private static final Queue<Integer> QUEUE = new LinkedList<>();

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 1, TimeUnit.HOURS, new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory());
        ProducerAndConsumerWaitNotify notify = new ProducerAndConsumerWaitNotify();
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
            while ( true ) {
                try {
                    Thread.sleep(200);
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
                synchronized ( QUEUE ) {
                    System.out.println(this.name + "获取生成者锁");
                    int queueMaxSize = 5;
                    while ( QUEUE.size() == queueMaxSize ) {
                        System.out.println(this.name + "发现仓库已满，不再进行生产，唤醒消费者消费！");
                        try {
                            QUEUE.wait(1000);
                            System.out.println(this.name + "被唤醒！");
                        } catch ( InterruptedException e ) {
                            e.printStackTrace();
                        }
                    }
                    int num = Double.valueOf(Math.random() * 1000).intValue();
                    System.out.println(this.name + "烤了只全羊:" + num);
                    QUEUE.offer(num);
                    QUEUE.notifyAll();
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

                synchronized ( QUEUE ) {
                    System.out.println(this.name + "获得锁！");
                    while ( QUEUE.isEmpty() ) {
                        System.out.println(this.name + "发现仓库空了，等待生产者烤羊！");
                        try {
                            QUEUE.wait();
                            System.out.println(this.name + "被唤醒！");
                        } catch ( InterruptedException e ) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(this.name + "吃了一只羊：" + QUEUE.poll());
                    QUEUE.notifyAll();
                }
            }
        }
    }
}