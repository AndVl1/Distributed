package ru.bmstu.iu9.destributed.lab2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DelaysReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
}