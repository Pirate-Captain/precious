/*
 * chsi
 * Created on 2018-04-13
 */
package com.zyl.lambda;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Map<String, String>> testMap = new HashMap<>(3);
        Map<String, String> resultMap = testMap.computeIfAbsent("10", k -> new HashMap<>(1));
        resultMap.put("a", "b");
        System.out.println(testMap.get("10").get("a"));
        System.out.println(resultMap.size());
    }
}