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
        Iterator<Text> iter = values.iterator();
        Text flight = iter.next();
        while (iter.hasNext()){
            Text delay = iter.next();
            Text outValue = new Text(flight.toString() + "\t" + delay.toString());
            context.write(new Text(Integer.toString(key.getAirportId())), outValue);
        }
    }
}
