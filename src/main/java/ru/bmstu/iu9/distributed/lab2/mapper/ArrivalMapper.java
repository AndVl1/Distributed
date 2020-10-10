package ru.bmstu.iu9.distributed.lab2.mapper;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.hadoop.io.DoubleWritable;
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
        String data = value.toString();
        CSVReader reader = new CSVReader(new StringReader(value.toString()), ',', '"', 1);

        String[] lines = data.split("\n");
        String nextLine;
        Text flightDelay = new Text();
        for(String line: lines) {
            int firstComma = 0;
            for (char symbol: line.toCharArray()){
                if (symbol == ','){
                    break;
                }
                firstComma++;
            }
            String info = line.substring(firstComma);
            int code = Integer.getInteger(line.substring(0, firstComma).replace('"', ' '));
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
            flightDelay.set(Double.toString(delay));
            context.write(new Key(airportCode, ARRIVAL_KEY), flightDelay);
        }

    }

    private final static int ARRIVAL_KEY = 1;
}
