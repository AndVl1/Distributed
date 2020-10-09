package ru.bmstu.iu9.distributed.lab2.partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

public class DataPartitioner<K, V> extends Partitioner<K, V> {

    @Override
    public int getPartition(K k, V v, int numPartitions) {
        if (!(k instanceof Key)) {

        } else if (!(v instanceof Text)) {

        }
        return 0;
    }
}
