/*
 * chsi
 * Created on 2018-02-26
 */
package com.zyl.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class DiskClassLoaderTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        DiskClassLoader diskClassLoader = new DiskClassLoader("d:/logs/classloader/");
        try {
            Class testHello = diskClassLoader.loadClass("com.my.test.TestHello");
            System.out.println(testHello.getClassLoader().toString());
            Object obj = testHello.newInstance();
            Method method = testHello.getDeclaredMethod("say", null);
            method.invoke(obj, null);
        } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e ) {
            e.printStackTrace();
        }
    }
}