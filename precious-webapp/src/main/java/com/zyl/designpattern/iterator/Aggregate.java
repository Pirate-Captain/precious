/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public interface Aggregate<T> {
    void add(T t);
    void remove(T t);
    Iterator<T> createIterator();
}
