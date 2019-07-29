/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public interface Iterator<T> {
    void first();
    T next();
    boolean hasNext();
}
