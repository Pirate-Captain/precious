/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * 不是线程安全的
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteArrayIterator<T> implements Iterator<T> {
    private ConcreteAggregateArray<T> aggregate;
    private int currentIndex = -1;
    
    public ConcreteArrayIterator(ConcreteAggregateArray<T> aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public void first() {
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < aggregate.size()-1;
    }

    @Override
    public T next() {
        currentIndex++;
        return currentIndex == -1 ? null : aggregate.getElement(currentIndex);
    }
}