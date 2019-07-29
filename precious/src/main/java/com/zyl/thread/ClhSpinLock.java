/*
 * chsi
 * Created on 2018-03-29
 */
package com.zyl.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁（spin lock）
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ClhSpinLock {
    private final ThreadLocal<Node> prev;
    private final ThreadLocal<Node> node;
    private final AtomicReference<Node> tail = new AtomicReference<>(new Node());

    public ClhSpinLock() {
        System.out.println("constructor");
        this.node = new ThreadLocal<Node>(){

            @Override
            protected Node initialValue() {
                System.out.println("initial");
                return new Node();
            }
        };
        this.prev = ThreadLocal.withInitial(() -> null);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public void lock() {
        System.out.println("lock, prelocknull:" + (null == prev.get()));
        final  Node currentNode = this.node.get();
        currentNode.locked = true;
        Node prevNode = tail.getAndSet(currentNode);
//        System.out.println("lock-prevnode-" + prevNode.name);
        this.prev.set(prevNode);
        while ( prevNode.locked ) {
            //进入自旋
        }
    }

    public void unlock() {
        final Node currentNode = this.node.get();
        currentNode.locked = false;
        this.node.set(this.prev.get());
//        System.out.println("unlock-node-" + node.get().name);
    }

    private static class Node {
        private volatile boolean locked;
        private String name;

        public Node() {
            name = Thread.currentThread().getName();
            System.out.println("create:" + name);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ClhSpinLock clhSpinLock = new ClhSpinLock();
        clhSpinLock.lock();

        for ( int i =0; i < 10; i++) {
            new Thread(() -> {
                clhSpinLock.lock();
                System.out.println(Thread.currentThread().getName() + "->" + clhSpinLock.prev.get().name);
//                System.out.println(Thread.currentThread().getId() + "acquire thre lock");
                clhSpinLock.unlock();
                System.out.println("unlock:" + Thread.currentThread().getName() + "->" + clhSpinLock.prev.get().name);
            }).start();
            Thread.sleep(1000);
        }


        System.out.println("main thread unlock!");
        clhSpinLock.unlock();
    }
}