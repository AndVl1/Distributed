package ru.bmstu.iu9.distributed.lab4;

import akka.actor.AbstractActor;

import java.util.HashMap;
import java.util.Map;

public class ResultsActor extends AbstractActor {
    private final Map<String, TestResult> testResults = new HashMap<>();


    @Override
    public Receive createReceive() {
        return null;
    }
}
