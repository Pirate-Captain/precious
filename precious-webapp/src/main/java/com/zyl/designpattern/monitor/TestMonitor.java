/**
 * Created on 2016-4-18
 */
package com.zyl.designpattern.monitor;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class TestMonitor {
    public static void main(String[] args) {
        ConcreteMonitor monitor = new ConcreteMonitor();
        ConcreteColleague1 colleague1 = new ConcreteColleague1(monitor);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(monitor);
        
        monitor.setConcreteColleague1(colleague1);
        monitor.setConcreteColleague2(colleague2);
        
        colleague1.showColleague();
        System.out.println();
        colleague2.showColleague();
    }
}