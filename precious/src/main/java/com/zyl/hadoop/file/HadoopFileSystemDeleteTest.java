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
 * @author zhuyl<a href="mailto:472130873@qq.com">zhu Youliang</a>
 * @version $Id$
 */
public class HadoopFileSystemDeleteTest {
    private static String uri = "";
    
    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), configuration);
        boolean recusive = false;
        fileSystem.delete(new Path(uri), recusive);
    }
}