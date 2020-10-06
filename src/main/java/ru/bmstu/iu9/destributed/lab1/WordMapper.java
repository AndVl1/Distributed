package ru.bmstu.iu9.destributed.lab1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException,
            InterruptedException {
        String textString = value.toString().toLowerCase();
        StringTokenizer tokenizer = new StringTokenizer(textString);
        while (tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken());
            context.write(word, new IntWritable(1)); // без хардкода на след лабы :)
        }
    }
}
