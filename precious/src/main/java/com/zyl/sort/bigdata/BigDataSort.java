/**
 * chsi
 * Created on 2017-09-15
 */
package com.zyl.sort.bigdata;

import com.google.common.collect.LinkedHashMultiset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class BigDataSort {
    private static int dataCount = 50000000;
    private static int dataSpliteCount = 50000;
    private static String dataSourcePath = "d:/logs/sort/bigdata/source/source.txt";
    private static String spliteDataPath = "d:/logs/sort/bigdata/split";
    private static String splitDataFileName = spliteDataPath + "/big_data_%s.txt";
    private static String sortedDataFileName = "d:/logs/sort/bigdata/result/sort.txt";

    public static void main(String[] args) throws IOException {
        generateBigData();
        long startTime = System.currentTimeMillis();
        System.out.println("数据生成完毕");
        spliteData();
        System.out.println("数据分割完毕");
        merger();
        System.out.println("数据排序完毕");
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
    }

    private static void generateBigData() throws IOException {
        File file = new File(dataSourcePath);
        if ( !file.getParentFile().exists() ) {
            file.getParentFile().mkdirs();
        }
        BufferedWriter br = new BufferedWriter(new FileWriter(dataSourcePath));
        Random random = new Random();
        for ( int i=0; i<dataCount; i++) {
            br.write((random.nextInt(dataCount)) + "\r\n");
        }
        br.close();
    }

    private static void spliteData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dataSourcePath));
        String line = "";
        int count = 1;
        List<Integer> dataList = new ArrayList<Integer>();
        while ( (line = br.readLine()) != null ) {
            dataList.add(Integer.parseInt(line));
            if ( dataList.size() >= dataSpliteCount ) {

                writeSpliteFile(count++, dataList);
            }
        }
        if ( !dataList.isEmpty() ) {
            writeSpliteFile(count++, dataList);
        }
        br.close();
    }

    private static void merger() throws IOException {
        List<FileInfo> fileList = new ArrayList<FileInfo>();
        File spliteFile = new File(spliteDataPath);
        File[] files = spliteFile.listFiles();
        for ( File tmpFile : files ) {
            fileList.add(new FileInfo(tmpFile.getAbsolutePath()));
        }

        File file = new File(sortedDataFileName);
        if ( !file.getParentFile().exists() ) {
            file.getParentFile().mkdirs();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(sortedDataFileName));
        while ( !fileList.isEmpty() ) {
            Collections.sort(fileList);
            bw.write(fileList.get(0).getCurrentData() + "\r\n");
            Integer nextValue = fileList.get(0).next();
            if ( null == nextValue ) {
                fileList.remove(0);
            }
        }
        bw.close();
    }

    private static void writeSpliteFile(int fileCount, List<Integer> multiset) throws IOException {
        Collections.sort(multiset);
        String fileName = String.format(splitDataFileName, fileCount);
        File file = new File(fileName);
        if ( !file.getParentFile().exists() ) {
            file.getParentFile().mkdirs();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        Iterator<Integer> iterator = multiset.iterator();
        while ( iterator.hasNext() ) {
            bw.write(iterator.next() + "\r\n");
        }
        bw.close();
        multiset.clear();
    }
}