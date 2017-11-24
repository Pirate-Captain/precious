/**
 * chsi
 * Created on 2017-11-06
 */
package com.zyl.base;

/**
 * protected的访问限制：
 * 5.子类中用同一个包下其他子类对象能访问父类protected属性或方法
 *
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ProtectedAccessChild2 extends ProtectedAccessParent {
    public static void main(String[] args) {
        ProtectedAccessChild child = new ProtectedAccessChild();
        System.out.println(child.getMemberCount());
    }
}