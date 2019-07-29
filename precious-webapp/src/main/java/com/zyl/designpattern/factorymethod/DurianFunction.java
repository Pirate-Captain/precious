/**
 * Created on 2016-4-11
 */
package com.zyl.designpattern.factorymethod;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class DurianFunction extends FruitFunction {

    @Override
    protected Fruit fruitFactoryMethod() {
        return new Durian();
    }
}