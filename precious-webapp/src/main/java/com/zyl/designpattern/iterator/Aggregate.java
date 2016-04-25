/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public interface Aggregate<T> {
    void add(T t);
    void remove(T t);
    Iterator<T> createIterator();
}
