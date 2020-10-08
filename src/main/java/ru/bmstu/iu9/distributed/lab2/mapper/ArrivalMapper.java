package ru.bmstu.iu9.distributed.lab2.mapper;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

import java.io.IOException;
import java.io.StringReader;

public class ArrivalMapper extends Mapper<LongWritable, Text, Key, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        CSVReader reader = new CSVReader(new StringReader(value.toString()), ',', '"', 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            int code;
        }

    }

    private final static int ARRIVAL_KEY = 1;
}
