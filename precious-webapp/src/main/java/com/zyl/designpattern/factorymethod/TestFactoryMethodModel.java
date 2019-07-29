/**
 * Created on 2016-4-11
 */
package com.zyl.designpattern.factorymethod;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class TestFactoryMethodModel {
    public static void main(String[] args) {
        FruitFunction appFunction = new AppleFunction();
        appFunction.fruitSmell();
        FruitFunction durianFunction = new DurianFunction();
        durianFunction.fruitSmell();
    }
}