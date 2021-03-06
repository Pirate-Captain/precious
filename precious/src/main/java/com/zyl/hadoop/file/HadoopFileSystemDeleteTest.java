/**
 * Created on 2017年4月9日
 */
package com.zyl.hadoop.file;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 删除文件
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class HadoopFileSystemDeleteTest {
    private static String uri = HadoopConfigUtil.getConfig("fs.defaultFS") + "/usr/hadoop";

    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(uri), configuration, HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        boolean recusive = false;
        fileSystem.delete(new Path(uri), recusive);
    }
}