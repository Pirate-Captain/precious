/**
 * Created on 2016-4-21
 */
package com.zyl.designpattern.observer;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ConcreteObserver1 implements Observer {
    private String observerName;

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(String notifyInfo) {
        System.out.println("ConcreteObserver1 was notified by push ,and the observerName is:" + observerName + ", the notifyInfo is " + notifyInfo);
    }

    @Override
    public void update(Subject subject) {
        System.out.println("ConcreteObserver1 was notified by pull,and the observerName is:" + observerName + ", the subject is " + subject.toString());
    }
}