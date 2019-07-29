/**
 * chsi
 * Created on 2017-05-25
 */
package com.zyl.xml.xstream;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class TestConvert {
    public static void main(String[] args) {
        XmlBean bean = new XmlBean();
        bean.setToUserName("dddd  ddd");
        bean.setFromUserName("oddiuidfjida");
        bean.setMsgType("text");
        bean.setCreateTime("d dd");

        ChildBean childBean = new ChildBean();
        childBean.setMediaId("helkdk");
        bean.setChildBean(childBean);

        ParentBean parentBean = bean;

        String xml = CDATAXStreamUtil.convertToXml(parentBean);
        System.out.println(xml);
    }
}