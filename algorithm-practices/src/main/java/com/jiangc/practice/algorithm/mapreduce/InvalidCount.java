package com.jiangc.practice.algorithm.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * 类名称：InvalidCount<br>
 * 类描述：实现对统计长度为0和以“开头的记录数记做丢失MISSING和无效INVALID<br>
 * 创建时间：2018年07月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class InvalidCount extends MapReduceBase implements Mapper<LongWritable,Text,Text,Text> {

    static enum SalesCounters {
        MISSING,
        INVALID
    }

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        //Input string is split using ',' and stored in 'fields' array
        String fields[] = value.toString().split(",",-20);
        //Value at 4th index is country. It is stored in 'country' variable
        String country = fields[4];

        //Value at 8th index is sales data. It is stored in 'sales' variable
        String sales = fields[8];

        if (country.length() == 0){
            reporter.incrCounter(SalesCounters.MISSING,1);
        } else if (sales.startsWith("\"")){
            reporter.incrCounter(SalesCounters.INVALID,1);
        } else {
            output.collect(new Text(country),new Text(sales + ",1"));
        }
    }
}
