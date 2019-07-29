/**
 * Created on 2016-4-8
 */
package com.zyl.designpattern.singleton;


/**
 * 懒汉模式
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class SingleTonLazyModel {
    private static SingleTonLazyModel instance = null;
    
    private SingleTonLazyModel() {
        System.out.println(Thread.currentThread().getName());
    }
    
    //非线程安全的
    public static SingleTonLazyModel getInstanceUnSafed() {
        if ( null == instance ) {
            try {
                Thread.sleep(1000);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            instance = new SingleTonLazyModel();
        }
        return instance;
    }
    
    //线程安全但效率不高
    public static synchronized SingleTonLazyModel getInstanceSafedButUnEfficent() {
        if ( null == instance ) {
            try {
                Thread.sleep(1000);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            instance = new SingleTonLazyModel();
        }
        return instance;
    }
    
    //双重校验效率比上面的高
    public static SingleTonLazyModel getInstanceSafedAndEfficent() {
        if ( null == instance ) {
            synchronized (SingleTonLazyModel.class) {
                if ( null == instance ) {
                    try {
                        Thread.sleep(1000);
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    }
                    instance = new SingleTonLazyModel();
                }
            }
        }
        return instance;
    }
    
    public static void main(String[] args) {
        test3();
    }
    
    public static void test1() {
        for ( int i=0; i<100; i++ ) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    SingleTonLazyModel.getInstanceUnSafed();
                }
            }).start();
        }
    }
    
    public static void test2() {
        for ( int i=0; i<100; i++ ) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    SingleTonLazyModel.getInstanceSafedButUnEfficent();
                }
            }).start();
        }
    }
    
    public static void test3() {
        for ( int i=0; i<100; i++ ) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    SingleTonLazyModel.getInstanceSafedAndEfficent();
                }
            }).start();
        }
    }
}