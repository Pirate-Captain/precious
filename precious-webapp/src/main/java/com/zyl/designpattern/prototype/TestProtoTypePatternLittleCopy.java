/**
 * Created on 2016-4-14
 */
package com.zyl.designpattern.prototype;

import java.util.ArrayList;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestProtoTypePatternLittleCopy {
    public static void main(String[] args) {
        ConcretePrototype1 protoType1 = new ConcretePrototype1();
        protoType1.setName("prototype1");
        protoType1.setDesc("原型模式1浅拷贝");
        PrototypeBean bean1 = new PrototypeBean();
        bean1.setName("bean1");
        bean1.setInfo("bean1 info");
        protoType1.setBean(bean1);
        ArrayList<String> cardList1 = new ArrayList<String>();
        cardList1.add("card1");
        cardList1.add("card2");
        protoType1.setCardNoList(cardList1);
        
        ConcretePrototype1 protoType1Clone = (ConcretePrototype1)protoType1.clone();
        System.out.println("浅拷贝：原对象中的name为：" + protoType1.getName());
        System.out.println("浅拷贝：原对象中的bean的name为：" + protoType1.getBean().getName());
        System.out.println("浅拷贝：原对象中的cardNoList的size为：" + protoType1.getCardNoList().size());
        System.out.println();
        
        protoType1Clone.setName("protoType1Clone");
        protoType1Clone.getBean().setName("protoType1CloneBean");
        protoType1Clone.getCardNoList().add("card3");
        System.out.println("浅拷贝：克隆对象中的name为：" + protoType1Clone.getName());
        System.out.println("浅拷贝：克隆对象中的bean的name为：" + protoType1Clone.getBean().getName());
        System.out.println("浅拷贝：克隆对象中的cardNoList的size为：" + protoType1Clone.getCardNoList().size());
        System.out.println();
        
        System.out.println("浅拷贝：原对象中的name为：" + protoType1.getName());
        System.out.println("浅拷贝：原对象中的bean的name为：" + protoType1.getBean().getName());
        System.out.println("浅拷贝：原对象中的cardNoList的size为：" + protoType1.getCardNoList().size());
    }
}