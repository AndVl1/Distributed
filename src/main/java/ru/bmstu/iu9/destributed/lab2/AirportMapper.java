package ru.bmstu.iu9.destributed.lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text location = new Text();
        String[] data = value.toString().split(",", -1);
    }

    private final int AIRPORT_KEY = 0;
}
