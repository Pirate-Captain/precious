/**
 * chsi
 * Created on 2017-10-11
 */
package com.zyl.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class WordCountMapReduceTask extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, WordCountMapReduceTask.class.getSimpleName());
        job.setJarByClass(WordCountMapReduceTask.class);

        job.setMapperClass(WordCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setCombinerClass(WordCountReducer.class);
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean isSuccess = job.waitForCompletion(true);

        return isSuccess ? 1 : 0;
    }

    public static void main(String[] args) throws Exception {
        args = new String[]{"hdfs://cluster1:9000/usr/hadoop/wordcount.txt", "hdfs://cluster1:9000/usr/hadoop/wordcount/result"};
        System.out.println(ToolRunner.run(new WordCountMapReduceTask(), args));
    }
}