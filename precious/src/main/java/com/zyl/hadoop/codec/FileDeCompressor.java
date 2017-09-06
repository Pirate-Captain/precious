/**
 * Created on 2017年4月10日
 */
package com.zyl.hadoop.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import com.zyl.hadoop.file.HadoopConfigUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

/**
 * 数据解压缩
 * @author zhuyl<a href="mailto:472130873@qq.com">zhu Youliang</a>
 * @version $Id$
 */
public class FileDeCompressor {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration configuration = new Configuration();
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(uri), configuration, HadoopConfigUtil.getConfig("hadoop.user"));
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        Path inputPath = new Path(uri);
        CompressionCodecFactory factory = new CompressionCodecFactory(configuration);
        CompressionCodec codec = factory.getCodec(inputPath);
        if ( null == codec ) {
            System.err.println("No codec found for " + uri);
            System.exit(1);
        }
        
        String outputUri = CompressionCodecFactory.removeSuffix(uri, codec.getDefaultExtension());
        InputStream in = null;
        OutputStream out = null;
        in = codec.createInputStream(fs.open(inputPath));
        out = fs.create(new Path(outputUri));
        IOUtils.copyBytes(in, out, configuration);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }
}