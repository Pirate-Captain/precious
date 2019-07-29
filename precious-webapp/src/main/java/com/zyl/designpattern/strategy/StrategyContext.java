/**
 * Created on 2016-5-4
 */
package com.zyl.designpattern.strategy;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class StrategyContext {
    private Strategy strategy;
    
    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public void useStrategy() {
        strategy.algorithmInterface(this);
    }
}