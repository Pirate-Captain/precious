/**
 * Created on 2016-4-18
 */
package com.zyl.designpattern.monitor;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Monitor monitor) {
        super(monitor);
    }
    
    public void showColleague() {
        this.getMonitor().change(this);
    }
    
    public void someOperate2() {
        System.out.println("I am ConcreteColleague2");
    }
}