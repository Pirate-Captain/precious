/*
 * chsi
 * Created on 2018-03-26
 */
package com.zyl.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class PredicateFunctionConsum {

    public static void printPersonEmail(List<PersonInfo> list, Predicate<PersonInfo> predicate, Function<PersonInfo, String> map, Consumer<String> consumer) {
        for ( PersonInfo p : list ) {
            if ( predicate.test(p) ) {
                consumer.accept(map.apply(p));
            }
        }
    }

    public static void main(String[] args) {
        List<PersonInfo> personInfoList = new ArrayList<>();
        personInfoList.add(new PersonInfo(20, "MALE", "a@chsi.com.cn"));
        personInfoList.add(new PersonInfo(10, "MALE", "b@chsi.com.cn"));
        personInfoList.add(new PersonInfo(30, "FEMALE", "c@chsi.com.cn"));

        printPersonEmail(personInfoList, p -> p.getAge() > 15 && p.getAge() < 30, PersonInfo::getEmail, System.out::println);
    }
}