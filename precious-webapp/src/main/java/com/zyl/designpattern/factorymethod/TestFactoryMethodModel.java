/**
 * Created on 2016-4-11
 */
package com.zyl.designpattern.factorymethod;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
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