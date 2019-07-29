/*
 * chsi
 * Created on 2018-08-21
 */
package com.zyl.zip;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class UnZipTest {
    private static final String UNZIP_FILE = "d:/logs/zip/pdfs.rar";
    private static final String OUT_FILE = "d:/logs/zip/unzip/";

    public static void main(String[] args) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UNZIP_FILE));
            ZipInputStream zis = new ZipInputStream(bis);
            ZipEntry zipEntry;
            while ( (zipEntry = zis.getNextEntry()) != null ) {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(OUT_FILE + zipEntry.getName()));
                byte[] tmp = new byte[1024];
                int length;
                while ( (length = zis.read(tmp)) != -1 ) {
                    bos.write(tmp, 0, length);
                }
                bos.flush();
                IOUtils.closeQuietly(bos);
            }
            zis.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }
}