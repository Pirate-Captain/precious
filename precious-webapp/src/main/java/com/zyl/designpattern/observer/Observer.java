/**
 * Created on 2016-4-21
 */
package com.zyl.designpattern.observer;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public interface Observer {
    /**
     * 推模式
     * @param notifyInfo
     */
    void update(String notifyInfo);
    /**
     * 拉模式
     * @param subject
     */
    void update(Subject subject);
}
