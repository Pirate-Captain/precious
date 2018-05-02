/*
 * chsi
 * Created on 2018-03-28
 */
package com.zyl.lambda;

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

        List<String> resultList = originalList.stream().filter("a"::equals).distinct().collect(Collectors.toList());
        resultList.forEach(System.out::println);
    }
}