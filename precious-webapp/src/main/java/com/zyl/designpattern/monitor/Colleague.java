/**
 * Created on 2016-4-18
 */
package com.zyl.designpattern.monitor;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public abstract class Colleague {
    private Monitor monitor;
    
    public Colleague(Monitor monitor) {
        this.monitor = monitor;
    }

    public Monitor getMonitor() {
        return monitor;
    }
}