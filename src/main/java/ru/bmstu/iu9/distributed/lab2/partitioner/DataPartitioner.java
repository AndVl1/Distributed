package ru.bmstu.iu9.distributed.lab2.partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

public class DataPartitioner extends Partitioner<Key, Text> {

    @Override
    public int getPartition(Key key, Text text, int numPartitions) {
        System.out.println("PARTITIONER " + key.getDataIndicator());
        return (Integer.hashCode(key.getAirportId() & Integer.MAX_VALUE) % numPartitions);
    }
}
