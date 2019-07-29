/**
 * Created on 2016-4-8
 */
package com.zyl.designpattern.singleton;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public enum SingleTon {
    INSTANCE;
    
    public void show() {
    }
    
    public SingleTon getInstance() {
        return INSTANCE;
    }
    
    public static void main(String[] args) {
        for ( int i=0; i<100; i++ ) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    SingleTon.INSTANCE.show();
                }
            }).start();
        }
    }
}