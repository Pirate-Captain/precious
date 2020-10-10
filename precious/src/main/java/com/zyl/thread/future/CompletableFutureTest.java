/*
 * chsi
 * Created on 2020-08-04
 */
package com.zyl.thread.future;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class CompletableFutureTest {
    private static LinkedBlockingQueue<RequestInfo> queue = new LinkedBlockingQueue<>();
    private static int COUNT = 1000;
    private static CountDownLatch countDownLatch = new CountDownLatch(COUNT);

    public static void main(String[] args) {
        for ( int index = 0; index < COUNT; index++ ) {
            new Thread(new BusinessThread(String.valueOf(index))).start();
            countDownLatch.countDown();
        }
        runTask();
    }

    private static void runTask() {
        new Thread(() -> {
            while ( true ) {
                try {
                    Thread.sleep(10);
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
                List<String> idList = new ArrayList<>();
                List<RequestInfo> requestList = new ArrayList<>();
                for ( int i = 0; i < queue.size(); i++ ) {
                    RequestInfo requestInfo = queue.poll();
                    requestList.add(requestInfo);
                    idList.add(requestInfo.getId());
                }
                Map<String, String> responseMap = new HashMap<>();
                // 业务功能
                for ( String id : idList ) {
                    responseMap.put(id, id);
                }
                for ( RequestInfo requestInfo : requestList ) {
                    requestInfo.future.complete(responseMap.get(requestInfo.getId()));
                }
            }
        }).start();
    }

    static class BusinessThread implements Runnable {
        private String id;

        public BusinessThread(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("-----run---" + id);
                countDownLatch.await();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setId(id);
            CompletableFuture<String> future = new CompletableFuture<>();
            requestInfo.setFuture(future);
            queue.add(requestInfo);
            try {
                System.out.println("id：" + id + "，rsp：" + future.get());
            } catch ( InterruptedException | ExecutionException e ) {
                e.printStackTrace();
            }
        }
    }

    static class RequestInfo {
        private String id;
        private CompletableFuture<String> future;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public CompletableFuture<String> getFuture() {
            return future;
        }

        public void setFuture(CompletableFuture<String> future) {
            this.future = future;
        }
    }
}