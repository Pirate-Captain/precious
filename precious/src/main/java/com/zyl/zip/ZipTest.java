/*
 * chsi
 * Created on 2018-08-21
 */
package com.zyl.zip;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ZipTest {
    private static final String FILE_DIR = "d:/logs/zip/pdfs";
    private static final String OUT_FILE = "d:/logs/zip/pdfs.rar";

    public static void main(String[] args) {
        File dirFile = new File(FILE_DIR);

        ZipOutputStream zos = null;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(OUT_FILE));
            zos = new ZipOutputStream(bos);
            for ( File file : dirFile.listFiles() ) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                byte[] tmp = new byte[1024];
                int length;
                while ( (length = bis.read(tmp)) != -1 ) {
                    zos.write(tmp, 0, length);
                }
                bis.close();
                zos.closeEntry();
            }
            zos.flush();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( null != zos ) {
                IOUtils.closeQuietly(zos);
            }
        }
    }
}