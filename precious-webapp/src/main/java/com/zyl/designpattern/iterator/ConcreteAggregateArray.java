/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteAggregateArray<T> implements Aggregate<T> {
    private Object[] elements = new Object[20];
    private int currentDataIndex = -1;
    
    @Override
    public void add(T t) {
        elements[++currentDataIndex] = t;
    }

    @Override
    public Iterator<T> createIterator() {
        return new ConcreteArrayIterator<T>(this);
    }

    @Override
    public void remove(T t) {
        throw new UnsupportedOperationException();
    }
    
    public int size() {
        return currentDataIndex + 1;
    }
    
    @SuppressWarnings("unchecked")
    public T getElement(int index) {
        return (T)elements[index];
    }
}