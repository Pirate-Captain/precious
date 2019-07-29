package com.zyl.java.thread;

/**
 * 为什么notify(), wait()等函数定义在Object中，而不是Thread中
 * <p>
 * Object中的wait(), notify()等函数，和synchronized一样，会对“对象的同步锁”进行操作。
 * <p>
 * wait()会使“当前线程”等待，因为线程进入等待状态，所以线程应该释放它锁持有的“同步锁”，否则其它线程获取不到该“同步锁”而无法运行！
 * OK，线程调用wait()之后，会释放它锁持有的“同步锁”；而且，根据前面的介绍，我们知道：等待线程可以被notify()或notifyAll()唤醒。
 * 现在，请思考一个问题：notify()是依据什么唤醒等待线程的？或者说，wait()等待线程和notify()之间是通过什么关联起来的？
 * 答案是：依据“对象的同步锁”。
 * <p>
 * notify与wait只能使用在synchronized代码中，必须获取对象锁，才能运行（并且要放到循环中，可以参悟 {@link com.zyl.java.thread.cosumerandproducer.ProducerAndConsumerWaitNotify}）
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class WaitAndNotify {
    public static void main(String[] args) {

    }
}