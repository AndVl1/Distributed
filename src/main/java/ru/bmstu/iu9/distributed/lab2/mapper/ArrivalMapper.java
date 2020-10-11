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
                airportCode = Integer.parseInt(fields[CODE_INDEX]);
                delay = fields[DELAY_INDEX];
                if (delay.equals(ZERO_DELAY_STRING)) {
                    continue;
                }
            } catch (Exception e) {
//                System.out.println(TAG + " " + e.getMessage());
                continue;
            }
            flightDelay.set(delay);
            context.write(new Key(airportCode, ARRIVAL_KEY), flightDelay);
        }

    }

    private final static int ARRIVAL_KEY = 1;
    private final static String CSV_DELIMITER = ",";
    private final static int CODE_INDEX = 14;
    private final static int DELAY_INDEX = 17;
    private final static String ZERO_DELAY_STRING = "0.00";
    private final static String TAG = "ARRIVAL MAPPER";
}
