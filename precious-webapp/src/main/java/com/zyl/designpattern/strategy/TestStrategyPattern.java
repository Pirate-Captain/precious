/**
 * Created on 2016-5-4
 */
package com.zyl.designpattern.strategy;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class TestStrategyPattern {
    public static void main(String[] args) {
        Strategy strategyA = new ConcreteStrategyA();
        StrategyContext contextA = new StrategyContext(strategyA);
        contextA.useStrategy();
        
        Strategy strategyB = new ConcreteStrategyB();
        StrategyContext contextB = new StrategyContext(strategyB);
        contextB.useStrategy();
        
        Strategy strategyC = new ConcreteStrategyC();
        StrategyContext contextC = new StrategyContext(strategyC);
        contextC.useStrategy();
    }
}