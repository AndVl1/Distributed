package ru.bmstu.iu9.distributed.lab2.comparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

public class GroupingComparator extends WritableComparator {
    public GroupingComparator() {
        super(Key.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return Integer.compare(((Key) a).getAirportId(), ((Key) b).getAirportId());
    }

    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        int id1 = readInt(b1, s1);
        int id2 = readInt(b2, s2);
        return Integer.compare(id1, id2);
    }
}
