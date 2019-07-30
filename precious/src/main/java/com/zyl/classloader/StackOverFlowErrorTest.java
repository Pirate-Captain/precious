/*
 * chsi
 * Created on 2019-07-29
 */
package com.zyl.classloader;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class StackOverFlowErrorTest {
    private int index = 0;

    private void method() {
        index++;
        method();
    }

    public static void main(String[] args) {
        StackOverFlowErrorTest test = new StackOverFlowErrorTest();
        try {
            test.method();
        } catch ( StackOverflowError error ) {
            System.out.println(error);
            System.out.println(test.index);
        }
    }
}