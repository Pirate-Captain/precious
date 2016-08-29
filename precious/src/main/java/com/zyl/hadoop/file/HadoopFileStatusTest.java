/**
 * Created on 2016年8月22日
 */
package com.zyl.hadoop.file;

import java.io.IOException;
import java.net.URI;

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
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        FileStatus fileStatus = fileSystem.getFileStatus(path);
        if ( null != fileStatus ) {
            // FileStatus对象封装了文件的和目录的额元数据，包括文件长度、块大小、权限等信息
            System.out.println("文件路径：" + fileStatus.getPath());
            System.out.println("块的大小：" + fileStatus.getBlockSize());
            System.out.println("文件所有者：" + fileStatus.getOwner() + ":" + fileStatus.getGroup());
            System.out.println("文件权限：" + fileStatus.getPermission());
            System.out.println("文件长度：" + fileStatus.getLen());
            System.out.println("备份数：" + fileStatus.getReplication());
            System.out.println("修改时间：" + fileStatus.getModificationTime());
        }
    }
}