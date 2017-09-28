/**
 * chsi
 * Created on 2017-09-28
 */
package com.zyl.levenshteindistance;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class LevenshteinDistanceTest {
    private static String str1 = "e";
    private static String str2 = "z";

    public static void main(String[] args) {
        System.out.println(Levenshteindistance.similarity(str1, str2));
    }
}