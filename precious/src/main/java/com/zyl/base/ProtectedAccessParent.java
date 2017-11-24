/**
 * chsi
 * Created on 2017-11-06
 */
package com.zyl.base;

/**
 * protected的访问限制：
 * 1.能被同一个包里的其他类访问
 * 2.能被子类访问（不在同一个包里也没问题）
 * 3.父类引用指向与父类不在同一个包下的子类（与父类同一个包下的子类是可以的）不能访问父类的protected属性或方法 ProtectedAccessChild1
 * 4.与父类不在同一个包下的子类中用户父类对象不能访问父类protected属性或方法
 * 5.子类中用不同包下（同一个包下是可以的）其他子类对象也不能访问父类protected属性或方法
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ProtectedAccessParent {
    protected int memberCount = 1;

    protected int getMemberCount() {
        return memberCount;
    }
}