/**
 * Created on 2016-4-13
 */
package com.zyl.innerclass;

/**
 * 1.内部类对象中不能有静态成员，原因很简单，内部类的实例对象是外部类实例对象的一个成员<br/>
 * 2.内部类就像一个实例成员一样存在于外部类中<br/>
 * 3.内部类可以访问外部类的所有成员就想访问自己的成员一样没有限制<br/>
 * 4.内部类中的this指的是内部类的实例对象本身，如果要用外部类的实例对象就可以用类名.this的方式获得<br/>
 * 5.在外部类外部，必须先创建外部类实例，然后再创建内部类实例
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class CommonInnerClass {
    private String unStaticField = "I am unstatic field";
    private static String staticField = "I am static field";
    
    public void unStaticMethod() {
        System.out.println("I am public unstatic method！");
    }
    
    public static void staticMethod() {
        System.out.println("I am public static method!");
    }
    
    private void unStaticPrivateMethod() {
        System.out.println("I am private unstatic method!");
    }
    
    private static void staticPrivateMethod() {
        System.out.println("I am private static method!");
    }
    
    public void getInnerClassInfo() {
        InnerClass inner = new InnerClass();
        inner.show();
    }
    
    class InnerClass {
        //普通内部类中不能定义静态的属性
        //private static String staticInnerField = "inner static field";
        
        public void show() {
            System.out.println(unStaticField);
            System.out.println(CommonInnerClass.this.unStaticField);
            System.out.println(staticField);
            System.out.println(CommonInnerClass.staticField);
            unStaticMethod();
            CommonInnerClass.this.unStaticMethod();
            staticMethod();
            CommonInnerClass.staticMethod();
            unStaticPrivateMethod();
            CommonInnerClass.this.unStaticPrivateMethod();
            staticPrivateMethod();
            CommonInnerClass.staticPrivateMethod();
        }
    }
    
    public static void main(String[] args) {
        new CommonInnerClass().getInnerClassInfo();
    }
}