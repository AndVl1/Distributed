package ru.bmstu.iu9.distributed.lab4;

import akka.actor.AbstractActor;

import java.util.HashMap;

public class ResultsActor extends AbstractActor {
    private final Map<String, /*testresult*/> testResults = new HashMap<>();


    @Override
    public Receive createReceive() {
        return null;
    }
}
