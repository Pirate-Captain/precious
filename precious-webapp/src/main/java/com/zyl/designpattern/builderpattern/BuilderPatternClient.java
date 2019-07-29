/**
 * Created on 2016-4-13
 */
package com.zyl.designpattern.builderpattern;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class BuilderPatternClient {
    public static void main(String[] args) {
        Builder builder1 = new ConcreteBuilder1();
        Director director1 = new Director(builder1);
        director1.createProduct();
        
        Builder builder2 = new ConcreteBuilder2();
        Director director2 = new Director(builder2);
        director2.createProduct();
    }
}