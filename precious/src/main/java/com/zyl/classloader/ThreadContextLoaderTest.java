/*
 * chsi
 * Created on 2018-02-26
 */
package com.zyl.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ThreadContextLoaderTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        DiskClassLoader diskClassLoader = new DiskClassLoader("d:/logs/classloader");
        Class test1;
        try {
            test1 = diskClassLoader.loadClass("com.my.test.TestHello");
            System.out.println(test1.getClassLoader().toString());

            Object object = test1.newInstance();

            Method method1 = test1.getMethod("say", null);
            method1.invoke(object, null);
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
        } catch ( InvocationTargetException e ) {
            e.printStackTrace();
        } catch ( InstantiationException e ) {
            e.printStackTrace();
        } catch ( NoSuchMethodException e ) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getContextClassLoader().toString());

        DiskClassLoader diskClassLoader1 = new DiskClassLoader("d:/logs/classloader/inner");

        new Thread(new Runnable() {
            @Override
            public void run() {
//                Thread.currentThread().setContextClassLoader(diskClassLoader);
                Thread.currentThread().setContextClassLoader(diskClassLoader1);
                System.out.println(Thread.currentThread().getContextClassLoader().toString());

                try {
                    Class test2 = Thread.currentThread().getContextClassLoader().loadClass("com.my.test.TestHello");
                    System.out.println(test2.getClassLoader().toString());
                    Object object2 = test2.newInstance();
                    Method method2 = test2.getMethod("say", null);
                    method2.invoke(object2, null);
                } catch ( ClassNotFoundException e ) {
                    e.printStackTrace();
                } catch ( IllegalAccessException e ) {
                    e.printStackTrace();
                } catch ( InstantiationException e ) {
                    e.printStackTrace();
                } catch ( NoSuchMethodException e ) {
                    e.printStackTrace();
                } catch ( InvocationTargetException e ) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}