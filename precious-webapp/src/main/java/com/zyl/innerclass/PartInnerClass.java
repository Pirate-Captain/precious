/**
 * Created on 2016-4-13
 */
package com.zyl.innerclass;

/**
 * 1.局部内部类的地位和方法内的局部变量的位置类似，因此不能修饰局部变量的修饰符也不能修饰局部内部类，譬如public、private、protected、static、transient等 <br>
 * 2.局部内部类只能在声明的方法内是可见的，因此定义局部内部类之后，想用的话就要在方法内直接实例化，记住这里顺序不能反了，一定是要先声明后使用，否则编译器会说找不到<br>
 * 3.局部内部类不能访问定义它的方法内的局部变量，除非这个变量被定义为final
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class PartInnerClass {
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
    
    public void showPartInnerInfo() {
        @SuppressWarnings("unused")
        String methodField = "methodField";
        
        final String methodFinalField = "final Field";
        
        class InnerClass {
            
            public void show() {
                //局部内部类不能访问定义它的方法内的局部变量，除非这个变量被定义为final
                //System.out.println(methodField);
                System.out.println(methodFinalField);
                
                System.out.println(unStaticField);
                System.out.println(PartInnerClass.this.unStaticField);
                System.out.println(staticField);
                System.out.println(PartInnerClass.staticField);
                unStaticMethod();
                PartInnerClass.this.unStaticMethod();
                staticMethod();
                CommonInnerClass.staticMethod();
                unStaticPrivateMethod();
                PartInnerClass.this.unStaticPrivateMethod();
                staticPrivateMethod();
                PartInnerClass.staticPrivateMethod();
            }
        }
        
        InnerClass innerClass = new InnerClass();
        innerClass.show();
    }
    
    public static void main(String[] args) {
        new PartInnerClass().showPartInnerInfo();
    }
}