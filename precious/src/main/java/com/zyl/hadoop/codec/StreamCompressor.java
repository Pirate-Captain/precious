/**
 * Created on 2017年4月10日
 */
package com.zyl.hadoop.codec;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * 数据压缩
 * @author zhuyl<a href="mailto:472130873@qq.com">zhu Youliang</a>
 * @version $Id$
 */
public class StreamCompressor {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String codeClassName = args[0];
        Class<?> codeClass = Class.forName(codeClassName);
        
        Configuration configuration = new Configuration();
        
        CompressionCodec codec = (CompressionCodec)ReflectionUtils.newInstance(codeClass, configuration);
        
        CompressionOutputStream out = codec.createOutputStream(System.out);
        IOUtils.copyBytes(System.in, out, 4096, false);
        out.flush();
    }
}