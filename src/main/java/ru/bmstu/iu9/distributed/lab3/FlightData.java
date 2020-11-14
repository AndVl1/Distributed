package ru.bmstu.iu9.distributed.lab3;

import scala.Serializable;

public class FlightData implements Serializable {
    private final String originId;
    private final String destinationId;
    private final int isCancelled;
    // also: maxDelay for collecting
    private final double delay;

    // for collecting flights
    private final int delayedOrCancelledCount;
    private final int totalCount;

    @Override
    public String toString() {
        return "FlightData{" +
                " maxDelay=" + delay +
                ", bad=" + delayedOrCancelledCount +
                ", total=" + totalCount +
                ", delayedOrCancelledPercentage=" + delayedOrCancelledCount / totalCount * 100 +
                '}';
    }

    public FlightData(String originId,
                      String destinationId,
                      int isCancelled,
                      double delay,
                      int delayedOrCancelledCount,
                      int totalCount) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.isCancelled = isCancelled;
        this.delay = delay;
        this.delayedOrCancelledCount = delayedOrCancelledCount;
        this.totalCount = totalCount;
        System.out.println("delayed / total " + delayedOrCancelledCount + " " + totalCount);
    }

    public FlightData(String originId, String destinationId, double delay) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.delay = delay;
        isCancelled = NOT_DELAYED_OR_CANCELLED;
        totalCount = 1;
        if (isDelayed()) {
            delayedOrCancelledCount = DELAYED_OR_CANCELLED;
        } else {
            delayedOrCancelledCount = NOT_DELAYED_OR_CANCELLED;
        }
    }

    public FlightData(String originId, String destinationId) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.delay = 0;
        isCancelled = DELAYED_OR_CANCELLED;
        totalCount = 1;
        delayedOrCancelledCount = DELAYED_OR_CANCELLED;
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
