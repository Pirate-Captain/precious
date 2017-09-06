/**
 * Created on 2017年4月9日
 */
package com.zyl.hadoop.file;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * FileSystem.GlobStatus
 * @author zhuyl<a href="mailto:472130873@qq.com">zhu Youliang</a>
 * @version $Id$
 */
public class HadoopFileSystemGlobStatusTest {
    private static String uri = HadoopConfigUtil.getConfig("fs.defaultFS") + "/usr/hadoop/";
    
    public static void main(String[] args) throws IOException {
//        matchStar();
//        matchQuestionMark();
//        matchOr();
//        matchNotOr();
//        matchPeriod();
//        matchNotPeriod();
//        matchOrInfo();
//        matchC();
        matchWithFilter();
    }
    
    /**
     * 匹配“*”（匹配0或多个字符）
     * @throws IOException 
     */
    private static void matchStar() throws IOException {
        //匹配"/*"的文件
        globStatus(uri+"*");
        
        //匹配"/*/*"的文件
        globStatus(uri+"*/*");
    }
    
    /**
     * 匹配“?”（匹配单一字符）
     * @throws IOException
     */
    private static void matchQuestionMark() throws IOException {
        globStatus(uri + "2016/1?");
    }
    
    /**
     * 匹配“[ab]”（匹配a或b）
     * @throws IOException
     */
    private static void matchOr() throws IOException {
        globStatus(uri + "2016/0[15]");
    }
    
    /**
     * 匹配“[^ab]”（匹配除a、b之外的）
     * @throws IOException
     */
    private static void matchNotOr() throws IOException {
        globStatus(uri + "2016/0[^15]");
    }
    
    /**
     * 匹配“[a-c]”（匹配a、b、c，即a到c之间的字母）
     * @throws IOException
     */
    private static void matchPeriod() throws IOException {
        globStatus(uri + "2016/0[1-9]");
    }
    
    /**
     * 匹配“[^a-c]”（匹配非a到c之间的字母）
     * @throws IOException
     */
    private static void matchNotPeriod() throws IOException {
        globStatus(uri + "2016/0[^1-7]");
    }
    
    /**
     * 匹配“{a,b}”（匹配a或b两个表达式中的一个，只要满足这俩表达式都行）
     */
    private static void matchOrInfo() throws IOException {
        globStatus(uri + "*/{02/*,12/*}");
    }
    
    /**
     * 匹配“c”
     */
    private static void matchC() throws IOException {
        globStatus(uri + "*/{02/*\\c*}");
    }
    
    /**
     * 带有正则表达式过滤
     */
    private static void matchWithFilter() throws IOException {
        globStatus(uri + "*", new HadoopFileSystemGlobStatusTest().new RegexIncludePatchFilter("05"));
    }
    
    private static void globStatus(String path) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(uri), configuration, HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        FileStatus[] fileStatusArr = fileSystem.globStatus(new Path(path));
        HadoopFileStatusShowUtil.showFileStatusArray(fileStatusArr);
    }
    
    private static void globStatus(String path, PathFilter pathFilter) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(uri), configuration, HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        FileStatus[] fileStatusArr = fileSystem.globStatus(new Path(path), pathFilter);
        HadoopFileStatusShowUtil.showFileStatusArray(fileStatusArr);
    }
    
    class RegexIncludePatchFilter implements PathFilter {
        private String regex;
        
        public RegexIncludePatchFilter(String regex) {
            this.regex = regex;
        }

        @Override
        public boolean accept(Path path) {
            return path.toString().contains(regex);
        }
        
    }
}