/**
 * Created on 2016-4-13
 */
package com.zyl.designpattern.builderpattern;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public interface Builder {
    void builderPartA();
    void builderPartB();
    void builderPartC();
    Product getProduct();
}