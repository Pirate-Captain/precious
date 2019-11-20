/*
 * chsi
 * Created on 2018-07-17
 */
package com.zyl.pdf;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class PdfboxTest {
    private static final String PDF_PATH = "d:/logs/pdf/wyw.pdf";

    public static void main(String[] args) {
        try {
            PDDocument document = PDDocument.load(new File(PDF_PATH));
            // 读文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            //获取内容
            System.out.println(stripper.getText(document));

            System.out.println("===================================");
            PDDocumentCatalog catalog = document.getDocumentCatalog();
            //读取文档信息
            PDDocumentInformation info = document.getDocumentInformation();
            PDMetadata metadata = catalog.getMetadata();

            System.out.println("Page Count=" + document.getNumberOfPages());
            System.out.println("Title=" + info.getTitle());
            System.out.println("Author=" + info.getAuthor());
            System.out.println("Subject=" + info.getSubject());
            System.out.println("Keywords=" + info.getKeywords());
            System.out.println("Creator=" + info.getCreator());
            System.out.println("Producer=" + info.getProducer());
            System.out.println("Creation Date=" + formatDate(info.getCreationDate()));
            System.out.println("Modification Date=" + formatDate(info.getModificationDate()));
            System.out.println("Trapped=" + info.getTrapped());
            if (metadata != null) {
                System.out.println("Metadata=" + metadata.getCOSObject());
            }

            PDPage page = document.getPage(0);
            PDResources resources = page.getResources();
            Iterable<COSName> objectNames = resources.getXObjectNames();
            for (COSName imageObjectName : objectNames) {
                if (page.getResources().isImageXObject(imageObjectName)) {
                    PDImageXObject pdImagexObject = (PDImageXObject) page.getResources().getXObject(imageObjectName);
                    BufferedImage bufferImage = pdImagexObject.getImage();
                    FileOutputStream fos = new FileOutputStream("d:/logs/pdf/img/" + Calendar.getInstance().getTimeInMillis() + ".jpg");
                    ImageIO.write(bufferImage, pdImagexObject.getSuffix(), fos);

                }
            }

            document.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private static String formatDate(Calendar date) {
        if ( null == date ) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date.getTime());
    }
}