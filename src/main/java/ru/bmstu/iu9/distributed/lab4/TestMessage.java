package ru.bmstu.iu9.distributed.lab4;

public class TestMessage extends TestRequest {
    public TestMessage(String packageId, String jsString, String functionName, TestParams[] tests) {
        super(packageId, jsString, functionName, tests);
    }
}
