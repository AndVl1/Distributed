package ru.bmstu.iu9.distributed.lab4;

public class RetrieveMessage {
    private final String packageId;

    public RetrieveMessage(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }
}
