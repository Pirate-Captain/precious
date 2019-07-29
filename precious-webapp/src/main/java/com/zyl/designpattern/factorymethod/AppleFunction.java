/**
 * Created on 2016-4-11
 */
package com.zyl.designpattern.factorymethod;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class AppleFunction extends FruitFunction {

    @Override
    protected Fruit fruitFactoryMethod() {
        return new Apple();
    }
}