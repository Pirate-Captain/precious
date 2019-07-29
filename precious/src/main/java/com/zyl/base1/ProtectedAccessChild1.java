/**
 * chsi
 * Created on 2017-11-06
 */
package com.zyl.base1;

import com.zyl.base.ProtectedAccessChild;
import com.zyl.base.ProtectedAccessParent;

/**
 * protected的访问限制：
 * 3.父类引用指向与父类不在同一个包下的子类（与父类同一个包下的子类是可以的）不能访问父类的protected属性或方法
 * 5.子类中用不同包下（同一个包下是可以的）其他子类对象也不能访问父类protected属性或方法
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ProtectedAccessChild1 extends ProtectedAccessParent {
    public static void main(String[] args) {
        ProtectedAccessChild child = new ProtectedAccessChild();
//        System.out.println(child.memberCount);

        ProtectedAccessParent parent = new ProtectedAccessChild1();
//        System.out.println(parent.memberCount);
    }
}