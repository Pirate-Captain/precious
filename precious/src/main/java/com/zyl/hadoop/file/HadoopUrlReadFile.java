package com.zyl.hadoop.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class HadoopUrlReadFile {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) {
        InputStream ins = null;
        try {
            ins = new URL(HadoopConfigUtil.getConfig("fs.defaultFS") + "/usr/hadoop/input.txt").openStream();
            IOUtils.copyBytes(ins, System.out, 1024, false);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(ins);
        }
    }
}