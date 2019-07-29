/**
 * Created on 2016-4-18
 */
package com.zyl.designpattern.monitor;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteMonitor implements Monitor {
    private ConcreteColleague1 concreteColleague1;
    private ConcreteColleague2 concreteColleague2;

    @Override
    public void change(Colleague colleague) {
        if ( colleague instanceof ConcreteColleague1 ) {
            System.out.println("ConcreteColleague1 changed");
            concreteColleague1.someOperate1();
        } else if ( colleague instanceof ConcreteColleague2 ) {
            System.out.println("ConcreteColleague2 changed");
            concreteColleague2.someOperate2();
        }
    }

    public void setConcreteColleague1(ConcreteColleague1 concreteColleague1) {
        this.concreteColleague1 = concreteColleague1;
    }

    public void setConcreteColleague2(ConcreteColleague2 concreteColleague2) {
        this.concreteColleague2 = concreteColleague2;
    }
}
