/**
 * Created on 2016-4-13
 */
package com.zyl.designpattern.builderpattern;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public interface Builder {
    void builderPartA();
    void builderPartB();
    void builderPartC();
    Product getProduct();
}