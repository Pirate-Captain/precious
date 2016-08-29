/**
 * Created on 2016年8月22日
 */
package com.zyl.hadoop.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class HadoopFileSystemWriteTest {
    private static String url = "hdfs://node:9000/usr/hadoop/input.txt";
    private static String localFile = "D:/summary/myprecious/test-input/hadoop/checkresult.log";
    
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        FSDataOutputStream fos = fileSystem.create(new Path(url), new Progressable() {
            
            @Override
            public void progress() {
                System.out.print(".");
            }
        });
        
        FileInputStream fis = new FileInputStream(localFile);
        byte[] buffer = new byte[2014];
        int length = 0;
        while ( (length = fis.read(buffer)) != -1 ) {
            fos.write(buffer, 0, length);
        }
        fos.hflush();
        fis.close();
        fos.close();
    }
}