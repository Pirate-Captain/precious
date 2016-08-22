/**
 * Created on 2016年8月22日
 */
package com.zyl.hadoop.file;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class HadoopFileStatusTest {
    private static String url = "hdfs://node:9000/usr/hadoop/input.txt";
    
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        
        Path path = new Path(url);
        FileSystem fileSystem = FileSystem.get(conf);
        FileStatus fileStatus = fileSystem.getFileStatus(path);
    }
}