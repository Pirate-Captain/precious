/**
 * Created on 2016-4-11
 */
package com.zyl.designpattern.factorymethod;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class DurianFunction extends FruitFunction {

    @Override
    protected Fruit fruitFactoryMethod() {
        return new Durian();
    }
}