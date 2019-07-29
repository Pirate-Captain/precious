/**
 * Created on 2016-5-4
 */
package com.zyl.designpattern.strategy;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteStrategyC implements Strategy {

    @Override
    public void algorithmInterface(StrategyContext context) {
        System.out.println("ConcreteStrategyC algorithm");
    }
}