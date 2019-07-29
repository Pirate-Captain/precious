/**
 * Created on 2016-4-13
 */
package com.zyl.designpattern.builderpattern;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteBuilder2 implements Builder {

    @Override
    public void builderPartA() {
        System.out.println("Build part A with ConcreteBuilder2");
    }

    @Override
    public void builderPartB() {
        System.out.println("Build part B with ConcreteBuilder2");
    }

    @Override
    public void builderPartC() {
        System.out.println("Build part C with ConcreteBuilder2");
    }

    @Override
    public Product getProduct() {
        return new ProductB();
    }
}