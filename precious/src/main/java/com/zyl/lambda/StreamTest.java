/*
 * chsi
 * Created on 2018-03-28
 */
package com.zyl.lambda;

import java.util.Random;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class StreamTest {
    public static void main(String[] args) {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }
}