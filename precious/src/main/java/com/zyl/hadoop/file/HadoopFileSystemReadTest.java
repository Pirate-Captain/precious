/**
 * Created on 2016年8月19日
 */
package com.zyl.hadoop.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 通过FileSystem来读取hadoop中的文件
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class HadoopFileSystemReadTest {
    public static void main(String[] args) throws IOException {
        String uri = HadoopConfigUtil.getConfig("fs.defaultFS") + "/usr/hadoop/input.txt";
        normalReadFromHDFS(uri);
        seekRead(uri);
    }
    
    /**
     * 正常读取
     * @param uri
     * @throws IOException
     */
    private static void normalReadFromHDFS(String uri) throws IOException {
        System.out.println("-----------------Nomal read from hdfs begin-----------------");
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(uri), configuration, HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        InputStream ins = fileSystem.open(new Path(uri));
        IOUtils.copy(ins, System.out);
        IOUtils.closeQuietly(ins);
        System.out.println("-----------------Nomal read from hdfs end------------------");
    }
    
    /**
     * 定位读取
     * @param uri
     * @throws IOException
     */
    private static void seekRead(String uri) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), configuration);
        FSDataInputStream ins = fileSystem.open(new Path(uri));
        IOUtils.copy(ins, System.out);
        ins.seek(0);
        System.out.println();
        IOUtils.copy(ins, System.out);
        IOUtils.closeQuietly(ins);
    }
}