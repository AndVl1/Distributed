package ru.bmstu.iu9.distributed.lab2.partitioner;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class DataPartitioner<K, V> extends Partitioner<K, V> {

    @Override
    public int getPartition(K k, V v, int numPartitions) {
        if ()
        return 0;
    }
}
