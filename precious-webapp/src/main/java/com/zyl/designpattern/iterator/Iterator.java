/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public interface Iterator<T> {
    void first();
    T next();
    boolean hasNext();
}
