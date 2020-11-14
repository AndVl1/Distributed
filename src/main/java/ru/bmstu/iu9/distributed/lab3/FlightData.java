package ru.bmstu.iu9.distributed.lab3;

import scala.Serializable;

public class FlightData implements Serializable {
    private final String originId;
    private final String destinationId;
    private final int isCancelled;
    private final double delay;

    // for collecting flights
    private int delayedOrCancelledCount;
    private int totalCount;

    @Override
    public String toString() {
        return "FlightData{" +
                "originId='" + originId + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", isCancelled=" + isCancelled +
                ", delay=" + delay +
                '}';
    }

    public FlightData(String originId,
                      String destinationId,
                      double delay,
                      int delayedOrCancelledCount,
                      int totalCount) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.delay = delay;
        this.delayedOrCancelledCount = delayedOrCancelledCount;
        this.totalCount = totalCount;
    }

    public FlightData(String originId, String destinationId, double delay) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.delay = delay;
        isCancelled = NOT_DELAYED_OR_CANCELLED;
        totalCount = 1;
    }

    public FlightData(String originId, String destinationId) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.delay = 0;
        isCancelled = DELAYED_OR_CANCELLED;
        totalCount = 1;
    }

    public int getDelayedOrCancelledCount() {
        return delayedOrCancelledCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public String getOriginId() {
        return originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public boolean isCancelled() {
        return isCancelled == DELAYED_OR_CANCELLED;
    }

    public double getDelay() {
        return delay;
    }

    public boolean isDelayed() {
        return delay > 0;
    }

    private final static int DELAYED_OR_CANCELLED = 1;
    private final static int NOT_DELAYED_OR_CANCELLED = 0;
}
