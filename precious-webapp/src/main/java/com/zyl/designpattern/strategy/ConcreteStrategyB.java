/**
 * Created on 2016-5-4
 */
package com.zyl.designpattern.strategy;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ConcreteStrategyB implements Strategy {

    @Override
    public void algorithmInterface(StrategyContext context) {
        System.out.println("ConcreteStrategyB algorithm");
    }
}