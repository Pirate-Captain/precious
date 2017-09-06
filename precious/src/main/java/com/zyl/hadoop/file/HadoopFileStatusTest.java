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
    private static String url = HadoopConfigUtil.getConfig("fs.defaultFS") + "/usr/hadoop";

    public static void main(String[] args) throws IOException {
        showFileStatus(url);
        System.out.println("------------------------------------------");
        showFileStatusList(url);
    }

    /**
     * 查看单个文件的文件状态
     *
     * @param uri
     * @throws IOException
     */
    private static void showFileStatus(String uri) throws IOException {
        Path path = new Path(uri);

        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(uri), new Configuration(), HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        FileStatus fileStatus = fileSystem.getFileStatus(path);
        HadoopFileStatusShowUtil.showDetailFileStatus(fileStatus);
    }

    /**
     * 显示文件列表信息
     *
     * @param uri
     * @throws IOException
     */
    private static void showFileStatusList(String uri) throws IOException {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(uri), new Configuration(), HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        FileStatus[] fileStatusArr = fileSystem.listStatus(new Path(uri));
        if ( null == fileStatusArr || fileStatusArr.length == 0 ) {
            return;
        }
        for ( FileStatus fileStatus : fileStatusArr ) {
            HadoopFileStatusShowUtil.showDetailFileStatus(fileStatus);
        }
    }
}