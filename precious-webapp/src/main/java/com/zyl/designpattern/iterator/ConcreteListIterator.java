/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcreteListIterator<T> implements Iterator<T> {
    private ConcreteAggregateList<T> aggregate;
    private int currentIndex = -1;
    
    public ConcreteListIterator(ConcreteAggregateList<T> aggregate) {
        this.aggregate = aggregate;
    }
    
    @Override
    public void first() {
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < (aggregate.getElementList().size() -1);
    }

    @Override
    public T next() {
        currentIndex++;
        return currentIndex == -1 ? null : aggregate.getElementList().get(currentIndex);
    }
}