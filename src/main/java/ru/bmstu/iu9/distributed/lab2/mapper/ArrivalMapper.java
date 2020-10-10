package ru.bmstu.iu9.distributed.lab2.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.distributed.lab2.writable.Key;
import java.io.IOException;

public class ArrivalMapper extends Mapper<LongWritable, Text, Key, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String[] lines = value.toString().split("\n");
        Text flightDelay = new Text();
        for (String line: lines) {
            String[] fields = line.split(",");
            int airportCode;
            double delay;
            try {
                airportCode = Integer.getInteger(fields[14]);
                delay = Double.parseDouble(fields[17]);
                if (delay == 0.0) {
                    continue;
                }
            } catch (Exception e) {
                System.out.println("aaa");
                continue;
            }
            flightDelay.set(Double.toString(delay));
            context.write(new Key(airportCode, ARRIVAL_KEY), flightDelay);
        }

    }

    private final static int ARRIVAL_KEY = 1;
}
