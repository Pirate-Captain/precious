/**
 * Created on 2016-4-11
 */
package com.zyl.designpattern.factorymethod;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Apple implements Fruit {

    @Override
    public void smell() {
        System.out.println("Smell good!");
    }
}