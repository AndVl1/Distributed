package ru.bmstu.iu9.distributed.lab2.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.distributed.AirportHelper;
import ru.bmstu.iu9.distributed.StringUtils;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, Key, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        Text description = new Text();

        String line = value.toString();

        if (key.get() == 0) {
            return;
        }

        AirportHelper airportInfo = StringUtils.trimCodeAndName(line, CSV_DELIMITER);
        description.set(
                airportInfo.getDescription()
        );
        context.write(new Key(airportInfo.getCode(), AIRPORT_KEY), description);

    }

    private final static int AIRPORT_KEY = 0;
    private final static char CSV_DELIMITER = ',';
    private final static String TAG = "AIRPORT MAPPER";
}
