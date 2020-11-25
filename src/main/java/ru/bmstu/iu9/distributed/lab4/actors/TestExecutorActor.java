package ru.bmstu.iu9.distributed.lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.iu9.distributed.lab4.TestMessage;
import ru.bmstu.iu9.distributed.lab4.TestResult;

public class TestExecutorActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, testMessage -> {
                    TestResult result =
                })
                .build();
    }

    private TestResult runTests(TestMessage message) throws Exception{
        
    }
}
