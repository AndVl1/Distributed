package ru.bmstu.iu9.distributed.lab4;

public class ResultMessage {
    public final String packageId;
    public final TestResult result;
    public final boolean isRequest;

    public ResultMessage(String packageId, TestResult result, boolean isRequest) {
        this.packageId = packageId;
        this.result = result;
        this.isRequest = isRequest;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "packageId='" + packageId + '\'' +
                ", result=" + result +
                '}';
    }
}
