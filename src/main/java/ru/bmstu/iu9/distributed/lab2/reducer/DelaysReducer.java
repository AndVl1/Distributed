package ru.bmstu.iu9.distributed.lab2.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import ru.bmstu.iu9.distributed.lab2.writable.Key;
import java.io.IOException;
import java.util.Iterator;

public class DelaysReducer extends Reducer<Key, Text, Text, Text> {
    @Override
    protected void reduce(Key key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException {
        Iterator<Text> vals = values.iterator();

        String name = vals.next().toString();

        if (!vals.hasNext()) {
            // There are no delays, skip that
            return;
        }

        double maxDelay = 0;
        double minDelay = 0;
        int delaysCount = 0;
        double sumDelay = 0;

        while (vals.hasNext()) {
            double delay = Double.parseDouble(vals.next().toString());
            sumDelay += delay;
            delaysCount++;
            if (delay > maxDelay) {
                delay = maxDelay;
            }
            if (delay < minDelay) {
                minDelay = delay;
            }
        }
        String res = MAX_DELAY_STRING + maxDelay + "\n" +
                MIN_DELAY_STRING + minDelay + "\n" +
                AVG_DELAY_STRING + sumDelay / delaysCount;
        context.write(new Text(name), new Text(res));
    }

    private final static String MAX_DELAY_STRING = "MAX: ";
    private final static String MIN_DELAY_STRING = "MIN: ";
    private final static String AVG_DELAY_STRING = "AVERAGE: ";
}
