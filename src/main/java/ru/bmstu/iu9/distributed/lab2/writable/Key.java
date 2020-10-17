package ru.bmstu.iu9.distributed.lab2.writable;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;


// IDK how to name it
public class Key implements WritableComparable<Key> {

    private int airportId;
    private int dataIndicator; // 0/1
    private static final int AIRPORT = 0;
    private static final int FLIGHT = 1;

    public Key() {
        set(0, 0);
    }

    public Key(int airportId, int dataIndicator) {
        set(airportId, dataIndicator);
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


    public void set(int airportId, int dataIndicator) {
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
    public int compareTo(Key o) {
        if (this.getAirportId() == o.getAirportId()) {
            return (getDataIndicator() == AIRPORT)? -1: 1;
        }
        return this.getAirportId() - o.getAirportId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return airportId == key.airportId;
    }

    @Override
    public String toString() {
        return "Key{" +
                "airportId=" + airportId +
                ", dataIndicator=" + dataIndicator +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportId);
    }
}
