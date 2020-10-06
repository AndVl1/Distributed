package ru.bmstu.iu9.destributed.lab2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

// TODO remane
public class SomeWritable implements WritableComparable<SomeWritable> {

    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {

    }

    @Override
    public int compareTo(SomeWritable o) {
        return 0;
    }


}
