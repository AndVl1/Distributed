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
        Iterator<Text> valuesIterator = values.iterator();

        String airportName = valuesIterator.next().toString();

        System.out.println("REDUCER " + key.toString() + airportName);

        if (!valuesIterator.hasNext()) {
            System.out.println("SKIP");
            return;
        }

        double maxDelay = 0;
        double minDelay = 0;
        int delaysCount = 0;
        double sumDelay = 0;

        while (valuesIterator.hasNext()) {
            String nextDelay = valuesIterator.next().toString();
            System.out.println(nextDelay);
            double delay;
            try {
                 delay = Double.parseDouble(nextDelay);
            } catch (Exception e) {
                System.err.println("err");
                continue;
            }
            sumDelay += delay;
            delaysCount++;
            if (delay > maxDelay) {
                delay = maxDelay;
            }
            if (delay < minDelay) {
                minDelay = delay;
            }
        }
        String res = airportName + "\n" +
                MAX_DELAY_STRING + maxDelay + "\n" +
                MIN_DELAY_STRING + minDelay + "\n" +
                AVG_DELAY_STRING + sumDelay / delaysCount + "\n\n";
        context.write(new Text(airportName), new Text(res));
    }

    private final static String MAX_DELAY_STRING = "MAX: ";
    private final static String MIN_DELAY_STRING = "MIN: ";
    private final static String AVG_DELAY_STRING = "AVERAGE: ";
}
