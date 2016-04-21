/**
 * Created on 2016-4-21
 */
package com.zyl.designpattern.observer;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestObserverPattern {
    public static void main(String[] args) {
        ConcreteObserver1 observer1 = new ConcreteObserver1();
        observer1.setObserverName("ConcreteObserver1-first");
        
        ConcreteObserver1 observer2 = new ConcreteObserver1();
        observer2.setObserverName("ConcreteObserver1-seconde");
        
        ConcreteObserver2 observer3 = new ConcreteObserver2();
        observer3.setMark("first");
        
        ConcreteObserver2 observer4 = new ConcreteObserver2();
        observer4.setMark("second");
        
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.addObserver(observer3);
        subject.addObserver(observer4);
        
        subject.setNotifyInfo("Hello everyone!");
    }
}