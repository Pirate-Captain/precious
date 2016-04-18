/**
 * Created on 2016-4-18
 */
package com.zyl.designpattern.monitor;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Monitor monitor) {
        super(monitor);
    }
    
    public void showColleague() {
        this.getMonitor().change(this);
    }
    
    public void someOperate1() {
        System.out.println("I am ConcreteColleague1");
    }
}