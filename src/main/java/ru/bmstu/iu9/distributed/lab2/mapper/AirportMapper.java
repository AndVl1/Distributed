package ru.bmstu.iu9.distributed.lab2.mapper;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

import java.io.IOException;
import java.io.StringReader;

public class AirportMapper extends Mapper<LongWritable, Text, Key, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        Text description = new Text();
        CSVReader reader = new CSVReader(new StringReader(value.toString()), ',', '"', 1);
        String[] nextLine;
        while (String airport : data) {
            String[] keyValue = airport.split(",");
            int code;
            try {
                code = Integer.getInteger(keyValue[0]);
            } catch (NumberFormatException e){
                // This is header, so we must just continue to next string
                continue;
            }
            description.set(keyValue[1]);
            context.write(new Key(code, AIRPORT_KEY), description);
        }
    }

    private final static int AIRPORT_KEY = 0;
}
