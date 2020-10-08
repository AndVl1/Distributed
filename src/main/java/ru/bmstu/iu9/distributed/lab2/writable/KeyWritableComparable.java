package ru.bmstu.iu9.distributed.lab2.writable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

// TODO rename
public class SomeWritable implements WritableComparable<SomeWritable> {

    private long AIRPORT_ID;
    private int dataIndicator;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SomeWritable that = (SomeWritable) o;
        return AIRPORT_ID == that.AIRPORT_ID &&
                dataIndicator == that.dataIndicator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(AIRPORT_ID, dataIndicator);
    }
}
