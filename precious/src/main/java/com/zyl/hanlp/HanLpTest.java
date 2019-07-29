/*
 * chsi
 * Created on 2018-03-13
 */
package com.zyl.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class HanLpTest {
    public static void main(String[] args) {
        String content = "当分词系统有一份词典的时候，就可以生成词图了。所谓词图，指的是句子中所有词可能构成的图。如果一个词A的下一个词可能是B的话，那么A和B之间具有一条路径E(A,B)。一个词可能有多个后续，同时也可能有多个前驱，它们构成的图我称作词图。";
        System.out.println(HanLP.segment(content));

        //标准分词
        System.out.println(StandardTokenizer.segment(content));
        //nlp分词
        System.out.println(NLPTokenizer.segment(content));
        //CRF分词
//        System.out.println(new CRFSegment().enablePartOfSpeechTagging(true).seg(content));
        //关键词提取
        System.out.println(HanLP.extractKeyword(content, 5));
        //摘要
        System.out.println(HanLP.extractSummary(content, 5));
        System.out.println(HanLP.extractPhrase(content, 5));
        System.out.println(HanLP.extractWords(content, 5));
    }
}