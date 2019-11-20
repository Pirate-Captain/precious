/*
 * chsi
 * Created on 2019-10-24
 */
package com.zyl.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用，在垃圾回收器线程扫描它 所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
 *
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<String> reference = new WeakReference<>(new String("hello weak reference"));
        System.out.println(reference.get());
        System.out.println("-------gc begin---------");
        System.gc();
        System.out.println("-------gc finish--------");
        System.out.println(reference.get());
    }
}