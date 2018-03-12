/*
 * chsi
 * Created on 2017-09-28
 */
package com.zyl.similarity;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class LevenshteinDistanceTest {

    public static void main(String[] args) {
        String str1 = "e";
        String str2 = "z";
        System.out.println(Levenshteindistance.similarity(str1, str2));
    }
}