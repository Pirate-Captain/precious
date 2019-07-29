/*
 * chsi
 * Created on 2018-03-26
 */
package com.zyl.lambda;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class PersonInfo {
    private int age;
    private String sex;
    private String email;

    public PersonInfo(int age, String sex, String email) {
        this.age = age;
        this.sex = sex;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}