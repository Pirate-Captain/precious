/**
 * Created on 2016-4-8
 */
package com.zyl.designpattern.singleton;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class SingleTonUnSafed {
    private static SingleTonUnSafed instance = null;
    
    private SingleTonUnSafed() {
        System.out.println(Thread.currentThread().getName());
    }
    
    public static SingleTonUnSafed getInstance() {
        if ( null == instance ) {
            try {
                Thread.sleep(1000);
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            instance = new SingleTonUnSafed();
        }
        return instance;
    }
    
    public static void main(String[] args) {
        for ( int i=0; i<100; i++ ) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    SingleTonUnSafed.getInstance();
                }
            }).start();
        }
    }
}