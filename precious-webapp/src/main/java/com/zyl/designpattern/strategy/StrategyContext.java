/**
 * Created on 2016-5-4
 */
package com.zyl.designpattern.strategy;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
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