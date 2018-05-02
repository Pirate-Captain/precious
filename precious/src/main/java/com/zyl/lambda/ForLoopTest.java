/*
 * chsi
 * Created on 2018-05-02
 */
package com.zyl.lambda;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ForLoopTest {
    private static final long DIVIC_NUMBER = 1;

    public static void main(String[] args) throws IOException {
        int[] numbers = new int[1000];
        int interval = 10;
        for ( int i =0; i <numbers.length; i++) {
            numbers[i] = interval * (i + 1);
        }

        Map<Integer, List<PersonInfo>> dataMap = new HashMap<>(numbers.length);
        for ( Integer number : numbers ) {
            dataMap.put(number, createPersonList(number));
        }

        Map<String, List<Long>> useTimeMap = new LinkedHashMap<>();
        for ( Integer number : numbers ) {
            List<PersonInfo> tmpList = dataMap.get(number);
            List<Long> method1List = useTimeMap.computeIfAbsent("method1", key -> new ArrayList<>());
            method1List.add(method1UseTime(dataMap.get(number)));
            List<Long> method2List = useTimeMap.computeIfAbsent("method2", key -> new ArrayList<>());
            method2List.add(method2UseTime(tmpList));
            List<Long> method3List = useTimeMap.computeIfAbsent("method3", key -> new ArrayList<>());
            method3List.add(method3UseTime(tmpList));
            List<Long> method4List = useTimeMap.computeIfAbsent("method4", key -> new ArrayList<>());
            method4List.add(method4UseTime(tmpList));
            List<Long> method5List = useTimeMap.computeIfAbsent("method5", key -> new ArrayList<>());
            method5List.add(method5UseTime(tmpList));
            List<Long> method6List = useTimeMap.computeIfAbsent("method6", key -> new ArrayList<>());
            method6List.add(method6UseTime(tmpList));
        }

        StringBuilder echartOptionBuilder = new StringBuilder();
        ClassPathResource classPathResource = new ClassPathResource("forLoopUseTime.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(classPathResource.getInputStream(), "UTF-8"));
        String line;
        while ( (line = br.readLine()) != null ) {
            echartOptionBuilder.append(line);
        }

        IOUtils.closeQuietly(br);

        String echartOption = echartOptionBuilder.toString();
        StringBuilder numberBuilder = new StringBuilder();
        for ( int number : numbers ) {
            numberBuilder.append(number).append(",");
        }
        echartOption = StringUtils.replace(echartOption, "#dataNumbers#", numberBuilder.substring(0, numberBuilder.length() - 1));

        for ( Map.Entry<String, List<Long>> entry : useTimeMap.entrySet() ) {
            StringBuilder showBuilder = new StringBuilder(entry.getKey()).append("\t");
            StringBuilder messageBuilder = new StringBuilder();
            entry.getValue().forEach(value -> {messageBuilder.append(value).append(",");wrapMessage(showBuilder, value);});
            echartOption = StringUtils.replace(echartOption, "#"+entry.getKey()+"#" ,messageBuilder.substring(0, messageBuilder.length() - 1));
        }
        System.out.println(echartOption);
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    private static long method1UseTime(List<PersonInfo> personInfoList) {
        return tryAndCalcAverageTime(personInfoList, list -> {
            for ( int index = 0; index < list.size(); index++) {
                PersonInfo personInfo = list.get(index);
                emptyMethod(personInfo);
            }
        });
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    private static long method2UseTime(List<PersonInfo> personInfoList) {
        return tryAndCalcAverageTime(personInfoList, list -> {
            for ( int index = 0, length = list.size(); index < length; index++) {
                PersonInfo personInfo = list.get(index);
                emptyMethod(personInfo);
            }
        });
    }

    @SuppressWarnings("WhileLoopReplaceableByForEach")
    private static long method3UseTime(List<PersonInfo> personInfoList) {
        return tryAndCalcAverageTime(personInfoList, list -> {
            Iterator<PersonInfo> iterator = list.iterator();
            while ( iterator.hasNext() ) {
                emptyMethod(iterator.next());
            }
        });
    }

    private static long method4UseTime(List<PersonInfo> personInfoList) {
        return tryAndCalcAverageTime(personInfoList, list -> {
            for ( PersonInfo personInfo : list) {
                emptyMethod(personInfo);
            }
        });
    }

    private static long method5UseTime(List<PersonInfo> personInfoList) {
        return tryAndCalcAverageTime(personInfoList, list -> list.forEach(ForLoopTest::emptyMethod));
    }

    private static long method6UseTime(List<PersonInfo> personInfoList) {
        long startTime = System.nanoTime();
        personInfoList.iterator().forEachRemaining(ForLoopTest::emptyMethod);
        return (System.nanoTime() - startTime) / DIVIC_NUMBER;
    }

    private static long tryAndCalcAverageTime(List<PersonInfo> personInfoList, Consumer<List<PersonInfo>> consumer) {
        int times = 1;
        long[] useTimeArray = new long[times];
        for ( int index = 0; index < times; index++ ) {
            long startTime = System.nanoTime();
            consumer.accept(personInfoList);
            useTimeArray[index] = System.nanoTime() - startTime;
        }
        Arrays.sort(useTimeArray);
        long[] tmpArray = times > 3 ? Arrays.copyOfRange(useTimeArray, 1, useTimeArray.length - 1) : useTimeArray;
        double average = Arrays.stream(tmpArray).average().orElse(0.0);
        return Double.valueOf(Math.floor(average)).longValue();
    }

    private static void wrapMessage(StringBuilder builder, long value) {
        int length = 8;
        String valueStr = String.valueOf(value);
        int blankLength = length - valueStr.length();
        if ( blankLength > 0 ) {
            for ( int index=0; index < blankLength; index++ ) {
                builder.append(" ");
            }
        }
        builder.append(value).append("\t");
    }

    private static List<PersonInfo> createPersonList(int number) {
        List<PersonInfo> personInfoList = new ArrayList<>();
        Random random = new Random();
        for ( int i = 0; i < number; i++ ) {
            personInfoList.add(new PersonInfo(random.nextInt(100), random.nextInt(1000) % 2 == 0 ? "MALE" : "FEMALE", "a" + i + "@a.com"));
        }
        return personInfoList;
    }

    @SuppressWarnings("unused")
    private static void emptyMethod(PersonInfo personInfo) {

    }
}