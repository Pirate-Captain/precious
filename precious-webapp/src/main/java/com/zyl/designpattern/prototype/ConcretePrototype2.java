/**
 * Created on 2016-4-14
 */
package com.zyl.designpattern.prototype;

import java.util.ArrayList;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ConcretePrototype2 extends Prototype {
    private ArrayList<String> cardNoList;
    private PrototypeBean bean;

    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        ConcretePrototype2 cloneObj = (ConcretePrototype2)super.clone();
        cloneObj.setBean((PrototypeBean)this.bean.clone());
        cloneObj.setCardNoList((ArrayList<String>)this.cardNoList.clone());
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