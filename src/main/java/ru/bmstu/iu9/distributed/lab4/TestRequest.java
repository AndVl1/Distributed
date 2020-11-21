package ru.bmstu.iu9.distributed.lab4;

public class TestRequest {
    private String packageId;
    private String jsString;
    private String functionName;
    private TestParams tests;

    public TestRequest(String packageId, String jsString, String functionName, TestParams tests) {
        this.packageId = packageId;
        this.jsString = jsString;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getJsString() {
        return jsString;
    }

    public void setJsString(String jsString) {
        this.jsString = jsString;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public TestParams getTests() {
        return tests;
    }

    public void setTests(TestParams tests) {
        this.tests = tests;
    }

    static class TestParams {
        private String testName;
        private String expectedResult;
        private int[] params;

        public TestParams(String testName, String expectedResult, int[] params) {
            this.testName = testName;
            this.expectedResult = expectedResult;
            this.params = params;
        }

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public String getExpectedResult() {
            return expectedResult;
        }

        public void setExpectedResult(String expectedResult) {
            this.expectedResult = expectedResult;
        }

        public int[] getParams() {
            return params;
        }

        public void setParams(int[] params) {
            this.params = params;
        }
    }
}
