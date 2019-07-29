/**
 * Created on 2016-4-13
 */
package com.zyl.innerclass;

/**
 * 因为静态嵌套类和其他静态方法一样只能访问其它静态的成员，而不能访问实例成员。因此静态嵌套类和外部类（封装类）之间的联系就很少了，他们之间可能也就是命名空间上的一些关联
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class StaticInnerClass {
    @SuppressWarnings("unused")
    private String unStaticField = "I am unstatic field";
    private static String staticField = "I am static field";
    
    public void unStaticMethod() {
        System.out.println("I am public unstatic method！");
    }
    
    public static void staticMethod() {
        System.out.println("I am public static method!");
    }
    
    @SuppressWarnings("unused")
    private void unStaticPrivateMethod() {
        System.out.println("I am private unstatic method!");
    }
    
    private static void staticPrivateMethod() {
        System.out.println("I am private static method!");
    }
    
    static class InnerClass {
        void show() {
            //不能访问实例成员
//            System.out.println(unStaticField);
//            System.out.println(StaticInnerClass.this.unStaticField);
            System.out.println(staticField);
            System.out.println(StaticInnerClass.staticField);
            
          //不能访问实例成员
//            unStaticMethod();
//            StaticInnerClass.this.unStaticMethod();
            staticMethod();
            CommonInnerClass.staticMethod();
            
            //不能访问实例成员
//            unStaticPrivateMethod();
//            StaticInnerClass.this.unStaticPrivateMethod();
            
            staticPrivateMethod();
            StaticInnerClass.staticPrivateMethod();
        }
    }
}