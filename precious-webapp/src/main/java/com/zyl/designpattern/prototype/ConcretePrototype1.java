/**
 * Created on 2016-4-14
 */
package com.zyl.designpattern.prototype;

import java.util.List;

/**
 * 
 * 原型模式浅拷贝
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ConcretePrototype1 extends Prototype {
    private List<String> cardNoList;
    private PrototypeBean bean;
    
    /**
     * 浅拷贝，cardNoList以及bean只是引用拷贝
     */
    @Override
    public Object clone() {
        return super.clone();
    }

    public List<String> getCardNoList() {
        return cardNoList;
    }

    public void setCardNoList(List<String> cardNoList) {
        this.cardNoList = cardNoList;
    }

    public PrototypeBean getBean() {
        return bean;
    }

    public void setBean(PrototypeBean bean) {
        this.bean = bean;
    }
}