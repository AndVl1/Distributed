package ru.bmstu.iu9.distributed.lab2.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.distributed.lab2.writable.Key;
import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, Key, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        Text description = new Text();

        String[] lines = value.toString().split("\n");

        for(String line : lines) {

            description.set(
                    line.substring(firstComma + 1).replace('"', '\0')
            );
            context.write(new Key(code, AIRPORT_KEY), description);
        }
    }

    private final static int AIRPORT_KEY = 0;
    private final static String CHARS_TO_REMOVE = "[\"\\s+]";
    private final static char CSV_DELIMITER = ',';
}
