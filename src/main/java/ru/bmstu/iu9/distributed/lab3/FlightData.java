package ru.bmstu.iu9.distributed.lab3;

import scala.Serializable;

public class FlightData implements Serializable {
    private final String originId;
    private final String destinationId;
    private final int isCancelled;

    @Override
    public String toString() {
        return "FlightData{" +
                "originId='" + originId + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", isCancelled=" + isCancelled +
                ", delay=" + delay +
                '}';
    }

    private final double delay;

    public FlightData(String originId, String destinationId, double delay) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.delay = delay;
        isCancelled = NOT_CANCELLED;
    }

    public FlightData(String originId, String destinationId) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.delay = 0;
        isCancelled = NOT_CANCELLED;
    }

    public String getOriginId() {
        return originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public int getIsCancelled() {
        return isCancelled;
    }

    public double getDelay() {
        return delay;
    }

    private final static int CANCELLED = 1;
    private final static int NOT_CANCELLED = 0;
}
