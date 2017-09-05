/**
 * chsi
 * Created on 2017-08-11
 */
package com.zyl.compress;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zhuyl<a href="mailto:472130873@qq.com">zhu Youliang</a>
 * @version $Id$
 */
public class ImageCompress {

    public static void main(String[] args) throws IOException {
        File file = new File("d:/f.png");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bufferByte = new byte[1024];
        int length = 0;
        FileInputStream fis = new FileInputStream(file);
        while ( (length = fis.read(bufferByte)) != -1 ) {
            bos.write(bufferByte, 0, length);
        }
        byte[] originalByte = bos.toByteArray();
//        byte[] compress = compressWithWidthRato(bos.toByteArray(), 100);
        byte[] compress = compressPhoto(originalByte, 842, 595);
        byte[] pdfByte = modifyImageIntoPDF(originalByte);
        if ( null != compress && compress.length > 0 ) {
            FileOutputStream fos = new FileOutputStream("d://small3.jpg");
            fos.write(compress);
            fos.close();
        }
        if ( null != pdfByte && pdfByte.length > 0 ) {
            FileOutputStream fos = new FileOutputStream("d://a.pdf");
            fos.write(pdfByte);
            fos.close();
        }
        fis.close();
        bos.close();
    }

    public static byte[] compressWithWidthRato(byte[] originalPhoto, int compress) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(originalPhoto));
            int width = image.getWidth();
            int height = image.getHeight();
            height = height * compress / width;
            return compressPhoto(originalPhoto, compress, height);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按给定大小压缩图片
     *
     * @param originalPhoto 原始图片
     * @param width         压缩后的宽度
     * @param height        压缩后的高度
     * @return 压缩后的图片字节数组
     */
    private static byte[] compressPhoto(byte[] originalPhoto, int width, int height) {
        ImageWriter imageWriter = null;
        ImageOutputStream ios = null;
        ByteArrayOutputStream bos = null;
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(originalPhoto));
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage(image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            bos = new ByteArrayOutputStream();


//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
//            encoder.encode(bufferedImage); // JPEG编码


            imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
            ios = ImageIO.createImageOutputStream(bos);
            imageWriter.setOutput(ios);
            IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(bufferedImage), null);

            imageWriter.write(imageMetaData, new IIOImage(bufferedImage, null, null), null);

            return bos.toByteArray();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if ( null != bos ) {
                    bos.close();
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            if ( null != ios ) {
                try {
                    ios.close();
                } catch ( IOException e ) {
                }
            }
            if ( null != imageWriter ) {
                try {
                    imageWriter.dispose();
                } catch ( Exception e ) {

                }
            }
        }
        return null;
    }

    private static byte[] modifyImageIntoPDF(byte[] xwzpByte) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Document document = null;
        try {
            Image image = Image.getInstance(xwzpByte);
            document = new Document(new Rectangle(image.getScaledWidth(), image.getScaledHeight()));
            image.setAbsolutePosition(0, 0);
            PdfWriter.getInstance(document, bos);
            document.open();
            document.add(image);
            document.close();
            return bos.toByteArray();
        } catch ( BadElementException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( DocumentException e ) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
        return null;
    }
}