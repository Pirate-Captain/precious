/**
 * Created on 2016-4-11
 */
package com.zyl.designpattern.factorymethod;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public abstract class FruitFunction {
    /**
     * 工厂方法
     * @return
     */
    protected abstract Fruit fruitFactoryMethod();
    
    public void fruitSmell() {
        fruitFactoryMethod().smell();
    }
}
