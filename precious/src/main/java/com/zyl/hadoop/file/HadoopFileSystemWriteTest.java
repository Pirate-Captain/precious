/**
 * Created on 2016年8月22日
 */
package com.zyl.hadoop.file;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class HadoopFileSystemWriteTest {
    private static String url = HadoopConfigUtil.getConfig("fs.defaultFS") + "/usr/hadoop/input.txt";
    private static String localFile = "D:\\logs\\securcrt\\192.168.41.129\\2017\\session-192.168.41.129-2017-09-05.log";
    
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(url), conf, HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {
        }
        if ( null == fileSystem ) {
            return;
        }
        FileInputStream fis = new FileInputStream(localFile);
        float totalCount = fis.available()/65536;
        FSDataOutputStream fos = fileSystem.create(new Path(url), new Progressable() {
            long fileCount = 0;
            @Override
            public void progress() {
                fileCount++;
                System.out.println("总进度" + (fileCount/totalCount)*100 + "%");
            }
        });
        
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