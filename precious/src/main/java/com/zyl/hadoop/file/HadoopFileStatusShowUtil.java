/**
 * Created on 2017年4月9日
 */
package com.zyl.hadoop.file;

import org.apache.hadoop.fs.FileStatus;

/**
 * @author zhuyl<a href="mailto:472130873@qq.com">zhu Youliang</a>
 * @version $Id$
 */
public class HadoopFileStatusShowUtil {
    /**
     * 查看文件的状态
     * @param fileStatus
     */
    public static void showDetailFileStatus(FileStatus fileStatus) {
        if ( null == fileStatus ) {
            return;
        }
        // FileStatus对象封装了文件的和目录的额元数据，包括文件长度、块大小、权限等信息
        System.out.println("文件路径：" + fileStatus.getPath());
        System.out.println("是否是文件：" + fileStatus.isFile());
        System.out.println("访问时间：" + fileStatus.getAccessTime());
        System.out.println("块的大小：" + fileStatus.getBlockSize());
        System.out.println("文件所有者：" + fileStatus.getOwner() + ":" + fileStatus.getGroup());
        System.out.println("文件权限：" + fileStatus.getPermission());
        System.out.println("文件长度：" + fileStatus.getLen());
        System.out.println("备份数：" + fileStatus.getReplication());
        System.out.println("修改时间：" + fileStatus.getModificationTime());
    }
    
    /**
     * 打印FileStatus的数组
     * @param fileStatusArr
     */
    public static void showFileStatusArray(FileStatus[] fileStatusArr) {
        if ( null == fileStatusArr || fileStatusArr.length == 0 ) {
            return;
        }
        
        for ( FileStatus fileStatus : fileStatusArr ) {
            System.out.println("-----------开始打印：" + fileStatus.getPath() + " 文件状态------------------");
            showDetailFileStatus(fileStatus);
            System.out.println();
        }
    }
}