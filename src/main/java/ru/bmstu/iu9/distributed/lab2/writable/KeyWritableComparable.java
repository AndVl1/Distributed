package ru.bmstu.iu9.distributed.lab2.writable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

// TODO rename
public class KeyWritableComparable implements WritableComparable<KeyWritableComparable> {

    private int airportId;
    private int dataIndicator; // 0/1
    private static final int AIRPORT = 0;
    private static final int FLIGHT = 1;

    public KeyWritableComparable(int airportId, int dataIndicator) {
        this.airportId = airportId;
        this.dataIndicator = dataIndicator;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public int getDataIndicator() {
        return dataIndicator;
    }

    public void setDataIndicator(int dataIndicator) {
        this.dataIndicator = dataIndicator;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(dataIndicator);
        out.writeInt(airportId);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        dataIndicator = in.readInt();
        airportId = in.readInt();
    }

    @Override
    public int compareTo(KeyWritableComparable o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyWritableComparable that = (KeyWritableComparable) o;
        return airportId == that.airportId &&
                dataIndicator == that.dataIndicator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportId, dataIndicator);
    }
}
