package ru.bmstu.iu9.distributed.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class ResultsActor extends AbstractActor {
    private final Map<String, TestResult> testResults = new HashMap<>();


    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                ResultMessage.class, resultMessage -> {
                    testResults.put(resultMessage.packageId, resultMessage.result);
                }
        ).match().build();
    }
}
