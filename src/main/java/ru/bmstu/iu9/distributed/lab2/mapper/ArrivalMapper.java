package ru.bmstu.iu9.distributed.lab2.mapper;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

import java.io.IOException;
import java.io.StringReader;

public class ArrivalMapper extends Mapper<LongWritable, Text, Key, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        CSVReader reader = new CSVReader(new StringReader(value.toString()), ',', '"', 1);
        String[] nextLine;
        DoubleWritable flightDelay = new DoubleWritable();
        while ((nextLine = reader.readNext()) != null) {
            int airportCode;
            double delay;
            try {
                airportCode = Integer.getInteger(nextLine[14]);
                delay = Double.parseDouble(nextLine[17]);
                if (delay == 0.0) {
                    continue;
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                continue;
            }
            flightDelay.set(delay);
            context.write(new Key(airportCode, ARRIVAL_KEY), flightDelay);
        }

    }

    private final static int ARRIVAL_KEY = 1;
}
