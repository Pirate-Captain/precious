/**
 * chsi
 * Created on 2017-10-11
 */
package com.zyl.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text mapOutKey = new Text();
    private final static IntWritable mapOutputValue = new IntWritable(1);

    /*
     * key是偏移量，value是一行一行的值 首先分割单词，组成key/value对进行输出
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = new String(value.getBytes(), 0, value.getLength(), "GBK").trim();
        StringTokenizer strToken = new StringTokenizer(line);
        while ( strToken.hasMoreTokens() ) {
            mapOutKey.set(strToken.nextToken());
            context.write(mapOutKey, mapOutputValue);
        }
    }
}