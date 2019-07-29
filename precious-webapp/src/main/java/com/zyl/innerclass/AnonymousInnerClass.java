/**
 * Created on 2016-4-13
 */
package com.zyl.innerclass;

/**
 * 1.匿名内部类可以是个接口<br/>
 * 2.匿名内部类用 new Pet(){ … } 的方式把声明类的过程和创建类的实例的过程合二为一<br/>
 * 3.匿名内部类可以是某个类的继承子类也可以是某个接口的实现类
 * 4.匿名内部类不能有静态成员
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class AnonymousInnerClass {
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
    
    interface InnerInterface {
        void showA();
        void showB();
    }
    
    abstract class InnerClass {
        abstract void showC();
    }
    
    public void showInnerInterface() {
        InnerInterface innerInterface = new InnerInterface(){
            //匿名内部类不能有静态成员
//            private static String static_A = "Hello";

            @Override
            public void showA() {
                System.out.println(unStaticField);
                System.out.println(AnonymousInnerClass.this.unStaticField);
                System.out.println(staticField);
                System.out.println(AnonymousInnerClass.staticField);
                
            }
            // 匿名内部类不能有静态成员
//            public static void showStatic() {
//                
//            }

            @Override
            public void showB() {
                unStaticMethod();
                AnonymousInnerClass.this.unStaticMethod();
                staticMethod();
                CommonInnerClass.staticMethod();
                unStaticPrivateMethod();
                AnonymousInnerClass.this.unStaticPrivateMethod();
                staticPrivateMethod();
                AnonymousInnerClass.staticPrivateMethod();
            }
            
        };
        
        innerInterface.showA();
        innerInterface.showB();
    }
    
    public void showInnerClass() {
        InnerClass innerClass = new InnerClass(){

            @Override
            void showC() {
                System.out.println(unStaticField);
                System.out.println(AnonymousInnerClass.this.unStaticField);
                System.out.println(staticField);
                System.out.println(AnonymousInnerClass.staticField);
                
                unStaticMethod();
                AnonymousInnerClass.this.unStaticMethod();
                staticMethod();
                CommonInnerClass.staticMethod();
                unStaticPrivateMethod();
                AnonymousInnerClass.this.unStaticPrivateMethod();
                staticPrivateMethod();
                AnonymousInnerClass.staticPrivateMethod();
            }
        };
        
        innerClass.showC();
    }
    
    public static void main(String[] args) {
        AnonymousInnerClass anonymousInnerClass = new AnonymousInnerClass();
        anonymousInnerClass.showInnerInterface();
        anonymousInnerClass.showInnerClass();
    }
}