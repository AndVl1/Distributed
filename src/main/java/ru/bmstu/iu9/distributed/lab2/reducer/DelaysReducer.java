package ru.bmstu.iu9.distributed.lab2.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import ru.bmstu.iu9.distributed.lab2.writable.Key;
import java.io.IOException;

public class DelaysReducer extends Reducer<Key, Text, Text, Text> {
    @Override
    protected void reduce(Key key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException {
        for (Text v: values) {
            context.write(new Text(key.getDataIndicator() + ""), new Text(v.toString()));
            context.write(new Text("Test 1"), new Text(v.toString()));
        }
    }
}
