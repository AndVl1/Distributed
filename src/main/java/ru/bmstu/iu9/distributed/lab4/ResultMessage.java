package ru.bmstu.iu9.distributed.lab4;

public class ResultMessage {
    private final String packageId;
    private final TestResult result;

    private final boolean isRequest;

    public ResultMessage(String packageId, TestResult result, boolean isRequest) {
        this.packageId = packageId;
        this.result = result;
        this.isRequest = isRequest;
    }

    public String getPackageId() {
        return packageId;
    }

    public TestResult getResult() {
        return result;
    }

    public boolean isRequest() {
        return isRequest;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "packageId='" + packageId + '\'' +
                ", result=" + result +
                '}';
    }
}
