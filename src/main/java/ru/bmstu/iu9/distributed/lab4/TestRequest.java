package ru.bmstu.iu9.distributed.lab4;

public class TestRequest {
    private String packageId;
    private String jsString;
    private String functionName;

    static class TestParams {
        private String testName;
        private String expectedResult;
        private int[] params;
    }
}
