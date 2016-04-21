/**
 * Created on 2016-4-21
 */
package com.zyl.designpattern.observer;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
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