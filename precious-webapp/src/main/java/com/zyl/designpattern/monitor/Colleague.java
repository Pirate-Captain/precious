/**
 * Created on 2016-4-18
 */
package com.zyl.designpattern.monitor;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
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