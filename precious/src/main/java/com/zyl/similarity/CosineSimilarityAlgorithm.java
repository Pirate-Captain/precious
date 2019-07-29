/*
 * chsi
 * Created on 2018-03-12
 */
package com.zyl.similarity;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 余弦定理-文本相似度算法
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class CosineSimilarityAlgorithm {
    private static double getSimilarity(String doc1, String doc2) {
        long startTime = System.currentTimeMillis();
        if ( StringUtils.isBlank(doc1) && StringUtils.isBlank(doc2) ) {
            return 1.0;
        }
        if ( StringUtils.isBlank(doc1) || StringUtils.isBlank(doc2) ) {
            return 0.0;
        }
        Map<Character, int[]> characterMap = new HashMap<>(doc1.length() / 2);
        calCharacter(doc1, 0, characterMap);
        calCharacter(doc2, 1, characterMap);

        double numerator = 0.0;
        double squareDoc1 = 0.0;
        double squareDoc2 = 0.0;
        for ( Map.Entry<Character, int[]> entry : characterMap.entrySet() ) {
            int[] tmpArray = entry.getValue();
            numerator += tmpArray[0] * tmpArray[1];
            squareDoc1 += tmpArray[0] * tmpArray[0];
            squareDoc2 += tmpArray[1] * tmpArray[1];
        }
        double result = numerator / Math.sqrt(squareDoc1 * squareDoc2);
        long endTime = System.currentTimeMillis();
        System.out.println("余弦定理比对耗时：" + (endTime - startTime));
        return result;
    }

    private static void calCharacter(String doc, int arrayIndex, Map<Character, int[]> characterMap) {
        for ( int index = 0; index < doc.length(); index++ ) {
            char docChar = doc.charAt(index);
            int[] characterArray = characterMap.get(docChar);
            if ( null == characterArray ) {
                characterArray = new int[2];
                characterArray[0] = 0;
                characterArray[1] = 0;
                characterMap.put(docChar, characterArray);
            }
            characterArray[arrayIndex] = characterArray[arrayIndex] + 1;
        }
    }

    public static void main(String[] args) {
        String doc1 = readFile("similarity/file1.txt");
        String doc2 = readFile("similarity/file2.txt");
        System.out.println(getSimilarity(doc1, doc2));
        System.out.println(Levenshteindistance.similarity(doc1, doc2));
    }

    private static String readFile(String fileName) {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ( (line = reader.readLine()) != null ) {
                sb.append(line);
            }
            return sb.toString();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return null;
    }
}