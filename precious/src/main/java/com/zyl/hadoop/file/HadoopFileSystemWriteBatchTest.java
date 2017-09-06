/**
 * Created on 2017年4月8日
 */
package com.zyl.hadoop.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * @author zhuyl<a href="mailto:472130873@qq.com">zhu Youliang</a>
 * @version $Id$
 */
public class HadoopFileSystemWriteBatchTest {
    private static String localFileDic = "D:\\logs\\securcrt\\192.168.41.129\\2017";
    private static String remoteFileDic = HadoopConfigUtil.getConfig("fs.defaultFS") + "/usr/hadoop";
    
    public static void main(String[] args) throws IOException {
        writeFile(new File(localFileDic));
    }
    
    private static void writeFile(File parentFile) throws IOException {
        if ( parentFile.isDirectory() ) {
            File[] childFiles = parentFile.listFiles();
            if ( null == childFiles || childFiles.length == 0 ) {
                return;
            }
            for ( File childFile : childFiles ) {
                writeFile(childFile);
            }
        } else {
            String filePath = StringUtils.replace(StringUtils.replace(parentFile.getAbsolutePath(), localFileDic, ""), "\\", "/");
            String remoteFileName = remoteFileDic + filePath;
            writeRemoteFile(parentFile, remoteFileName);
        }
    }
    
    private static void writeRemoteFile(File file, String remoteFileName) throws IOException {
        System.out.println("---------------------Write the " + remoteFileName + " begin--------------");
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(remoteFileName), configuration, HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {

        }
        OutputStream fos = fileSystem.create(new Path(remoteFileName));
        FileInputStream fis = new FileInputStream(file);
        IOUtils.copy(fis, fos);
        
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(fos);
        System.out.println("---------------------Write the " + remoteFileName + " end ----------------");
    }
}