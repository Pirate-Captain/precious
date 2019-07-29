/**
 * Created on 2016-4-21
 */
package com.zyl.designpattern.observer;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteObserver2 implements Observer {
    private String mark;
    
    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public void update(String notifyInfo) {
        System.out.println("ConcreteObserver2 is notified by push, the mark is " + mark + ", the notifyInfo is " + notifyInfo);
    }

    @Override
    public void update(Subject subject) {
        System.out.println("ConcreteObserver2 is notified by pull, the mark is " + mark + ", the subject is " + subject.toString());
    }
}