/**
 * Created on 2016-4-8
 */
package com.zyl.designpattern.singleton;

/**
 * 延迟加载模式（使用内部静态类来保证线程同步）
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class SingleTonLazyLoadModel {
    private SingleTonLazyLoadModel() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(30000);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        System.out.println("初始化....");
    }
    
    private static class SingleTonHolder {
        private static SingleTonLazyLoadModel instance = new SingleTonLazyLoadModel();
    }
    
    public static SingleTonLazyLoadModel getInstance() {
        return SingleTonHolder.instance;
    }
    
    public static void main(String[] args) {
        for ( int i=0; i<100; i++) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    SingleTonLazyLoadModel.getInstance();
                }
            }).start();
        }
    }
}