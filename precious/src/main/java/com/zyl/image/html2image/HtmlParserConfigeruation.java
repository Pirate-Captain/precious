/*
 * chsi
 * Created on 2017-11-24
 */
package com.zyl.image.html2image;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class HtmlParserConfigeruation {
    private static final int DEFAULT_HEIGHT = 1024;
    private static final int DEFAULT_WIDTH = 768;
    private static final String DEFAULT_FORMAT = "png";

    private int width = DEFAULT_WIDTH;
    private int heigth = DEFAULT_HEIGHT;
    private String imageFormat = DEFAULT_FORMAT;
    private boolean authoHeigth = true;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public boolean isAuthoHeigth() {
        return authoHeigth;
    }

    public void setAuthoHeigth(boolean authoHeigth) {
        this.authoHeigth = authoHeigth;
    }
}