/*
 * chsi
 * Created on 2017-09-28
 */
package com.zyl.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class BloomFilterTest {
    private static final int COUNT = 1000000;
    private static final double WC = 0.001;
    private static Map<String, String> tmpMap = new HashMap<>(COUNT);
    private static List<String> tmpList = new ArrayList<>();

    public static void main(String[] args) {
        BloomFilter filter = BloomFilter.create(Funnels.stringFunnel(), COUNT, WC);
        for ( int index = 0; index < COUNT; index++ ) {
            String uuid = UUID.randomUUID().toString();
            filter.put(uuid);
            tmpMap.put(uuid, uuid);
            tmpList.add(uuid);
        }

        int rightCount = 0;
        int failCount = 0;
        int wrongCount = 0;
        for ( int index = 0; index < COUNT; index++ ) {
            boolean inFilter = index % 10 == 0;
            String testId = inFilter ? tmpList.get(index) : UUID.randomUUID().toString();
            if ( filter.mightContain(testId) ) {
                if ( null == tmpMap.get(testId) ) {
                    failCount++;
                } else {
                    rightCount++;
                }
            } else {
                wrongCount = inFilter ? wrongCount + 1 : wrongCount;
                if ( null == tmpMap.get(testId) ) {
                    rightCount++;
                } else {
                    failCount++;
                }
            }
        }
        System.out.print("wrongCount：" + wrongCount + "，rightCount：" + rightCount + "，failCount：" + failCount + "，wrong ratios：" + (failCount / (double) WC));
    }
}