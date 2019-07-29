/**
 * chsi
 * Created on 2017-11-06
 */
package com.zyl.base;

/**
 * 3.父类引用指向与父类不在同一个包下的子类（与父类同一个包下的子类是可以的）不能访问父类的protected属性或方法
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ProtectedAccessChild extends ProtectedAccessParent {
    public static void main(String[] args) {
        ProtectedAccessChild child = new ProtectedAccessChild();
        System.out.println(child.memberCount);
        System.out.println(child.getMemberCount());

        ProtectedAccessParent parent = new ProtectedAccessParent();
        System.out.println(parent.memberCount);
        System.out.println(parent.getMemberCount());

        ProtectedAccessParent parent1 = new ProtectedAccessChild();
        System.out.println(parent1.memberCount);
        System.out.println(parent1.getMemberCount());
    }
}