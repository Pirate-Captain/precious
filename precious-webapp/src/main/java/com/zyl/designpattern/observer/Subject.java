/**
 * Created on 2016-4-21
 */
package com.zyl.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Subject {
    private List<Observer> observerList = new ArrayList<Observer>();
    
    /**
     * 添加观察者
     * @param observer
     */
    public synchronized void addObserver(Observer observer) {
        observerList.add(observer);
    }
    
    /**
     * 删除观察者
     * @param observer
     */
    public synchronized void removeObserver(Observer observer) {
        observerList.remove(observer);
    }
    
    /**
     * 推模式
     * @param notifyInfo
     */
    public void notifyAllObserver(String notifyInfo) {
        for ( Observer observer : observerList ) {
            observer.update(notifyInfo);
        }
    }
    
    /**
     * 拉模式
     * @param subject
     */
    public void notifyAllObserver(Subject subject) {
        for ( Observer observer : observerList ) {
            observer.update(this);
        }
    }
}