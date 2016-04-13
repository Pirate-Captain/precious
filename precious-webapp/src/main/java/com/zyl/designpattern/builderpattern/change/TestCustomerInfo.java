/**
 * Created on 2016-4-13
 */
package com.zyl.designpattern.builderpattern.change;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestCustomerInfo {
    public static void main(String[] args) {
        CustomerInfo.CustomerBuilder builder = new CustomerInfo.CustomerBuilder();
        CustomerInfo customInfo = builder.setName("张三").setSex("MALE").setAge(28).setAddress("海淀区").setCardNo("123456").setIdNum("654321").builder();
        System.out.println(customInfo.getName());
    }
}