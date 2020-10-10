package ru.bmstu.iu9.distributed.lab2.comparator;

import org.apache.hadoop.io.WritableComparator;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

public class GroupingComparator extends WritableComparator {
    public GroupingComparator() {
        super(Key.class);
    }
}
