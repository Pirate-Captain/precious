/**
 * Created on 2016-4-11
 */
package com.zyl.designpattern.factorymethod;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
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
