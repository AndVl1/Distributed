package ru.bmstu.iu9.distributed.lab2.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

import java.io.IOException;

public class DelaysReducer extends Reducer<Key, Text, Text, LongWritable> {

}
