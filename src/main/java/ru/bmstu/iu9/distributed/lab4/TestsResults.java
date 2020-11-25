package ru.bmstu.iu9.distributed.lab4;

import java.util.HashMap;
import java.util.Map;

public class TestResult {
    private final Map<String, Boolean> results;

    public TestResult(Map<String, Boolean> results) {
        this.results = results;
    }

    public TestResult() {
        results = new HashMap<>();
    }

    public void addPassedTest(String testName) {
        results.put(testName, true);
    }

    public void addFailedTest(String testName) {
        results.put(testName, false);
    }


}
