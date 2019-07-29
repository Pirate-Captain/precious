/*
 * chsi
 * Created on 2018-03-26
 */
package com.zyl.lambda;

import java.util.function.Consumer;

/**
 * 1.lambda表达式可以使用成员变量以及final的局部变量
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class VariablesLevel {
    private int x = 1;

    class FirstLevel {
        public int x = 2;

        public void methodFirstLevel(int x) {
            //如果再给x赋值，x将不再是局部的有效的final类型，这样lambda表达式中将会报错
//            x = 99;
            Consumer<Integer> consumer = (y) -> {
                System.out.println("x = " + x);
                System.out.println("y= " + y);
                System.out.println("this.x = " + this.x);
                System.out.println("VariablesLevel.this.x= " + VariablesLevel.this.x);
            };
            consumer.accept(x);
        }
    }


    public static void main(String[] args) {
        VariablesLevel level = new VariablesLevel();
        FirstLevel firstLevel = level.new FirstLevel();
        firstLevel.methodFirstLevel(23);
    }
}