/*
 * chsi
 * Created on 2018-11-19
 */
package com.zyl.retry;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestRetry {
    public static void main(String[] args) {
        System.out.println("begin");
        int index = 0;
        retry:
        for ( ; index < 10; index++ ) {
            System.out.println("index = :" + index);
            if ( 8 == index ) {
                System.out.println("go to retry");
                continue retry;
            }
        }
    }
}