/**
 * Created on 2016-5-4
 */
package com.zyl.designpattern.strategy;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public interface Strategy {
    /**
     * 策略方法
     * @param context
     */
    void algorithmInterface(StrategyContext context);
}