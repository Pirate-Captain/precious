/**
 * Created on 2016-4-21
 */
package com.zyl.designpattern.observer;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
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
