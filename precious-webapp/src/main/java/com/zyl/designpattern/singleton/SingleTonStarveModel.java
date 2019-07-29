/**
 * Created on 2016-4-8
 */
package com.zyl.designpattern.singleton;

/**
 * 饿汉模式
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class SingleTonStarveModel {
    private static SingleTonStarveModel instance = new SingleTonStarveModel();
    
    private SingleTonStarveModel() {
        System.out.println(Thread.currentThread().getName());
    }
    
    public static SingleTonStarveModel getInstance() {
        try {
            Thread.sleep(1000);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        return instance;
    }
    
    public static void main(String[] args) {
        for ( int i=0; i<100; i++ ) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    SingleTonStarveModel.getInstance();
                }
            }).start();
        }
    }
}