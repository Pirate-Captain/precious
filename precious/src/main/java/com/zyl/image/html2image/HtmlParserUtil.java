/*
 * chsi
 * Created on 2017-11-24
 */
package com.zyl.image.html2image;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.xerces.parsers.DOMParser;
import org.cyberneko.html.HTMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xhtmlrenderer.simple.Graphics2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class HtmlParserUtil {
    private static Logger log = LoggerFactory.getLogger(HtmlParserUtil.class);

    public byte[] convertHtmlToImageFromFile(File file, HtmlParserConfigeruation configeruation) {
        if ( null == file || null == configeruation ) {
            return null;
        }
        return convertHtmlToImage(new InputSource(file.toURI().toString()), configeruation);
    }

    public byte[] convertHtmlToImageFromString(String html, HtmlParserConfigeruation configeruation) {
        if ( StringUtils.isBlank(html) || null == configeruation ) {
            return null;
        }
        StringReader stringReader = new StringReader(html);
        try {
            return convertHtmlToImage(new InputSource(stringReader), configeruation);
        } finally {
            IOUtils.closeQuietly(stringReader);
        }
    }

    private byte[] convertHtmlToImage(InputSource inputSource, HtmlParserConfigeruation configeruation) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DOMParser domParser = getDomParser();
        try {
            domParser.parse(inputSource);
            Document document = domParser.getDocument();

            BufferedImage bufferedImage = getBufferedImage(document, configeruation);

            FSImageWriter imageWriter = new FSImageWriter(configeruation.getImageFormat());
            imageWriter.write(bufferedImage, bos);
            return bos.toByteArray();
        } catch ( SAXException e ) {
            log.error("将转换为图片异常：{}", e);
        } catch ( IOException e ) {
            log.error("将转换为图片异常：{}", e);
        } finally {
            IOUtils.closeQuietly(bos);
        }
        return null;
    }

    private BufferedImage getBufferedImage(Document document, HtmlParserConfigeruation configeruation) {
        Graphics2DRenderer renderer = new Graphics2DRenderer();
        renderer.setDocument(document, document.getDocumentURI());
        Dimension dimension = new Dimension(configeruation.getWidth(), configeruation.getHeigth());
        BufferedImage bufferedImage = new BufferedImage(configeruation.getWidth(), configeruation.getHeigth(), BufferedImage.TYPE_INT_RGB);

        if ( configeruation.isAuthoHeigth() ) {
            Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
            renderer.layout(graphics2D, new Dimension(configeruation.getWidth(), configeruation.getHeigth()));
            graphics2D.dispose();

            Rectangle size = renderer.getMinimumSize();
            final int autoWidth = (int) size.getWidth();
            final int autoHeight = (int) size.getHeight();
            bufferedImage = new BufferedImage(autoWidth, autoHeight, BufferedImage.TYPE_INT_RGB);
            dimension = new Dimension(autoWidth, autoHeight);
        }

        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        renderer.layout(graphics2D, dimension);
        renderer.render(graphics2D);
        graphics2D.dispose();

        return bufferedImage;
    }

    private DOMParser getDomParser() {
        DOMParser domParser = new DOMParser(new HTMLConfiguration());
        try {
            domParser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
        } catch ( SAXNotRecognizedException e ) {
            throw new RuntimeException(e);
        } catch ( SAXNotSupportedException e ) {
            throw new RuntimeException(e);
        }
        return domParser;
    }
}