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
        double delaysCount = 0;
        int sumDelay = 0;

        while (vals.hasNext()) {
            
        }
    }
}
