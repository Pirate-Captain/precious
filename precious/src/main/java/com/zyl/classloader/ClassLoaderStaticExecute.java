/*
 * chsi
 * Created on 2019-07-29
 */
package com.zyl.classloader;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ClassLoaderStaticExecute {
    static {
        System.out.println("hello class loader");
    }
}