/*
 * chsi
 * Created on 2018-07-17
 */
package com.zyl.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class PdfboxTest {
    private static final String PDF_PATH = "d:/logs/pdf/cjd.pdf";

    public static void main(String[] args) throws IOException {
        try {
            PDDocument document = PDDocument.load(new File(PDF_PATH));
            // 读文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            //获取内容
            System.out.println(stripper.getText(document));
            document.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}