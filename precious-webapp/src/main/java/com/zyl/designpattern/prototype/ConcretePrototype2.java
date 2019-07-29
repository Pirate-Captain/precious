/**
 * Created on 2016-4-14
 */
package com.zyl.designpattern.prototype;

import java.util.ArrayList;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcretePrototype2 extends Prototype {
    private ArrayList<String> cardNoList;
    private PrototypeBean bean;

    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        ConcretePrototype2 cloneObj = (ConcretePrototype2)super.clone();
        if ( null != bean ) {
            cloneObj.setBean((PrototypeBean)this.bean.clone());
        }
        if ( null != cardNoList ) {
            cloneObj.setCardNoList((ArrayList<String>)this.cardNoList.clone());
        }
        return cloneObj;
    }

    public ArrayList<String> getCardNoList() {
        return cardNoList;
    }

    public void setCardNoList(ArrayList<String> cardNoList) {
        this.cardNoList = cardNoList;
    }

    public PrototypeBean getBean() {
        return bean;
    }

    public void setBean(PrototypeBean bean) {
        this.bean = bean;
    }
}