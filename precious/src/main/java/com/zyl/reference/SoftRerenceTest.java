/*
 * chsi
 * Created on 2019-10-24
 */
package com.zyl.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用，如果内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class SoftRerenceTest {
    public static void main(String[] args) {
        SoftReference<String> reference = new SoftReference<>(new String("hello soft reference"));
        System.out.println(reference.get());
        System.out.println("-------gc begin---------");
        System.gc();
        System.out.println("-------gc finish--------");
        System.out.println(reference.get());
    }
}