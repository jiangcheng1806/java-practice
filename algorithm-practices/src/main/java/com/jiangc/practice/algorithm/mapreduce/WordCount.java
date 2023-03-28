package com.jiangc.practice.algorithm.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 类名称：WordCount<br>
 * 类描述：<br>
 * 创建时间：2018年07月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class WordCount {

    public static class TokenizerMapper extends Mapper<Object,Text,Text,IntWritable> {

        public static final IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(Object key,Text value,Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()){
                this.word.set(itr.nextToken());
                context.write(this.word,one);
            }
        }

    }

    public static class IntSumReduce extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Mapper.Context context) throws IOException, InterruptedException {
            int sum = 0;
            IntWritable val;
            for (Iterator i = values.iterator();i.hasNext();sum += val.get()){
                val = (IntWritable) i.next();
            }
            this.result.set(sum);
            context.write(key,this.result);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        FileUtil.deleteDir("output");
        Configuration conf = new Configuration();

        String[] otherArgs = new String[]{"input/dream.txt","output"};
        if (otherArgs.length != 2){
            System.err.println("Usage:Merge and duplicate removal <in> <out>");
            System.exit(2);
        }

        Job job = Job.getInstance(conf,"WordCount");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(IntSumReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,new Path((otherArgs[1])));
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
