/*
 * chsi
 * Created on 2019-07-29
 */
package com.zyl.classloader;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ClassLoaderStaticExecuteTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = TestHello.class.getClassLoader();
        System.out.println(loader);
        //Classloader.load方法加载类，不会执行初始化方法
        loader.loadClass("com.zyl.classloader.ClassLoaderStaticExecute");
        System.out.println("ClassLoader.load执行完毕");
        //使用Class.forName方法来加载类，默认会执行初始化块
        Class.forName("com.zyl.classloader.ClassLoaderStaticExecute");
        System.out.println("Class.forName执行完毕");
        //使用Clss.forName加载类，可以指定要不要初始化
        Class.forName("com.zyl.classloader.ClassLoaderStaticExecute", false, loader);
    }
}