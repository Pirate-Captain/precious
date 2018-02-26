/*
 * chsi
 * Created on 2018-02-26
 */
package com.zyl.classloader;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class DiskClassLoader extends ClassLoader {
    private String libPath;

    public DiskClassLoader(String libPath) {
        this.libPath = libPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = getFileName(name);
        File file = new File(libPath, className);
        FileInputStream fis = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(file);
            byte[] tmp = new byte[1024];
            int len;
            while ( (len = fis.read(tmp)) != -1 ) {
                bos.write(tmp, 0, len);
            }
            return defineClass(name, bos.toByteArray(), 0, bos.size());
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(bos);
        }
        return super.findClass(name);
    }

    private String getFileName(String className) {
        int dotIndex = className.lastIndexOf(".");
        if ( -1 == dotIndex ) {
            return className + ".class";
        }
        return className.substring(dotIndex + 1) + ".class";
    }
}