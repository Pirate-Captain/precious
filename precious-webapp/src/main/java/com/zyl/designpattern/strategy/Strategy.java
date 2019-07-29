/**
 * Created on 2016-5-4
 */
package com.zyl.designpattern.strategy;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public interface Strategy {
    /**
     * 策略方法
     * @param context
     */
    void algorithmInterface(StrategyContext context);
}