/*
 * chsi
 * Created on 2018-03-28
 */
package com.zyl.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class StreamTest {
    public static void main(String[] args) {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        List<String> originalList = Arrays.asList("a", "b", "c", "a", "d");

        List<String> resultList = originalList.stream().filter("z"::equals).distinct().collect(Collectors.toList());
        System.out.println(resultList.size());
        resultList.forEach(System.out::println);
        System.out.println(originalList.size());

        System.out.println(originalList.stream().filter("m"::equals).findFirst().orElse(null));

        List<String> emptyList = new ArrayList<>();
        emptyList.sort(String::compareTo);

        List<PersonInfo> personalInfoList = new ArrayList<>();
        List<PersonInfo> tmpPersonalInfoList = new ArrayList<>();
        tmpPersonalInfoList.addAll(personalInfoList);
        System.out.println(tmpPersonalInfoList.size());
        for ( PersonInfo personInfo : personalInfoList ) {
            System.out.println(personInfo.getAge());
        }

        String[] pinYin = new String[]{"chao", "chao", "zhao", "chao"};
        System.out.println(Arrays.stream(pinYin).distinct().collect(Collectors.toList()).toArray(new String[0]).length);
    }
}