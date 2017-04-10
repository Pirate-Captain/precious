/**
 * Created on 2017年4月10日
 */
package com.zyl.hadoop.codec;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * @author zhuyl<a href="mailto:472130873@qq.com">zhu Youliang</a>
 * @version $Id$
 */
public class PooledStreamCompressor {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String codecClassName = args[0];
        Class<?> codecClass = Class.forName(codecClassName);
        
        Configuration configuration = new Configuration();
        CompressionCodec codec = (CompressionCodec)ReflectionUtils.newInstance(codecClass, configuration);
        
        Compressor compressor = null;
        try {
            compressor = CodecPool.getCompressor(codec);
            CompressionOutputStream out = codec.createOutputStream(System.out, compressor);
            IOUtils.copyBytes(System.in, out, 4096, false);
            out.flush();
        } finally {
            CodecPool.returnCompressor(compressor);
        }
    }
}