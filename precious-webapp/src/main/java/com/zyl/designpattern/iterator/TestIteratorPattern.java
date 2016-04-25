/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestIteratorPattern {
    public static void main(String[] args) {
        ConcreteAggregateArray<SalaryBean> arrayAggregate = new ConcreteAggregateArray<SalaryBean>();
        arrayAggregate.add(new SalaryBean("张三", 10000, "开发"));
        arrayAggregate.add(new SalaryBean("李四", 10001, "设计"));
        arrayAggregate.add(new SalaryBean("王五", 10000, "测试"));
        
        ConcreteAggregateList<SalaryBean> listAggregate = new ConcreteAggregateList<SalaryBean>();
        listAggregate.add(new SalaryBean("张三1", 10000, "开发"));
        listAggregate.add(new SalaryBean("李四1", 10001, "设计"));
        listAggregate.add(new SalaryBean("王五1", 10000, "测试"));
        
        Iterator<SalaryBean> arrayIterator = arrayAggregate.createIterator();
        while ( arrayIterator.hasNext() ) {
            SalaryBean bean = arrayIterator.next();
            System.out.println("ConcreteAggregateArray：" + bean.getName() + "，" + bean.getSalary() + "，" + bean.getDepartmentName());
        }
        System.out.println();
        
        Iterator<SalaryBean> listIterator = listAggregate.createIterator();
        while ( listIterator.hasNext() ) {
            SalaryBean bean = listIterator.next();
            System.out.println("ConcreteAggregateList：" + bean.getName() + "，" + bean.getSalary() + "，" + bean.getDepartmentName());
        }
    }
}