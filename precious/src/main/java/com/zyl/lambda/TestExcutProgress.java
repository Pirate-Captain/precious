/*
 * chsi
 * Created on 2019-02-18
 */
package com.zyl.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestExcutProgress {
    public static void main(String[] args) {
        List<PersonInfo> list = new ArrayList<>();

        list.add(new PersonInfo(20, "MALE", "a@aa.com"));
        list.add(new PersonInfo(15, "FEMALE", "b@aa.com"));
        list.add(new PersonInfo(25, "MALE", "c@aa.com"));

        list.stream().filter(p -> {
            System.out.println("filter：" + p.getEmail());
            return p.getAge() > 18;
        }).map(p -> {
            System.out.println("map：" + p.getEmail());
            return p.getEmail();
        }).collect(Collectors.toList());
    }
}