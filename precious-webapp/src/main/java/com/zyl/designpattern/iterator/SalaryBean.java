/**
 * Created on 2016-4-25
 */
package com.zyl.designpattern.iterator;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class SalaryBean {
    private String name;
    private int salary;
    private String departmentName;

    /**
     * @param name
     * @param salary
     * @param departmentName
     */
    public SalaryBean(String name, int salary, String departmentName) {
        super();
        this.name = name;
        this.salary = salary;
        this.departmentName = departmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}