/**
 * Created on 2016-4-13
 */
package com.zyl.designpattern.builderpattern;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ConcreteBuilder1 implements Builder {

    @Override
    public void builderPartA() {
        System.out.println("Build part A with ConcreteBuilder1");
    }

    @Override
    public void builderPartB() {
        System.out.println("Build part B with ConcreteBuilder1");
    }

    @Override
    public void builderPartC() {
        System.out.println("Build part C with ConcreteBuilder1");
    }

    @Override
    public Product getProduct() {
        return new ProductA();
    }
}