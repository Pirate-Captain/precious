/**
 * Created on 2016-4-21
 */
package com.zyl.designpattern.observer;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteSubject extends Subject {
    private String notifyInfo;

    public void setNotifyInfo(String notifyInfo) {
        this.notifyInfo = notifyInfo;
        this.notifyAllObserver(notifyInfo);
        this.notifyAllObserver(this);
    }

    public String getNotifyInfo() {
        return notifyInfo;
    }
}