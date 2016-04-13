/**
 * Created on 2016-4-13
 */
package com.zyl.designpattern.builderpattern;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Director {
    private Builder builder;
    
    public Director(Builder builder) {
        this.builder = builder;
    }
    
    public Product createProduct() {
        builder.builderPartA();
        builder.builderPartB();
        builder.builderPartC();
        return builder.getProduct();
    }
}