/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ConcreteAggregateList<T> implements Aggregate<T> {
    private List<T> iteratorElementList = new ArrayList<T>();
    
    @Override
    public void add(T t) {
        iteratorElementList.add(t);
    }

    @Override
    public Iterator<T> createIterator() {
        return new ConcreteListIterator<T>(this);
    }

    @Override
    public void remove(T t) {
        iteratorElementList.remove(t);
    }
    
    public List<T> getElementList() {
        return iteratorElementList;
    }
}
