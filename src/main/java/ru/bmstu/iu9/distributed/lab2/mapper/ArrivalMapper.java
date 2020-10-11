package ru.bmstu.iu9.distributed.lab2.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.distributed.StringUtils;
import ru.bmstu.iu9.distributed.lab2.writable.Key;
import java.io.IOException;

public class ArrivalMapper extends Mapper<LongWritable, Text, Key, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String[] lines = value.toString().split("\n");
        Text flightDelay = new Text();
        for (String line: lines) {

            String[] fields = StringUtils.trimSimpleCsv(line, CSV_DELIMITER);
            int airportCode;
            String delay;
            try {
                airportCode = Integer.parseInt(fields[14]);
                delay = fields[17];
                if (delay.equals("0.00")) {
                    continue;
                }
            } catch (Exception e) {
                System.out.println("err " + e.getMessage());
                continue;
            }
            flightDelay.set(delay);
            context.write(new Key(airportCode, ARRIVAL_KEY), flightDelay);
        }

    }

    private final static int ARRIVAL_KEY = 1;
    private final static String CSV_DELIMITER = ",";
}
