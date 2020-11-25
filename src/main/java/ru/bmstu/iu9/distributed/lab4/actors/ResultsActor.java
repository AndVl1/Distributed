package ru.bmstu.iu9.distributed.lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.iu9.distributed.lab4.ResultMessage;
import ru.bmstu.iu9.distributed.lab4.RetrieveMessage;
import ru.bmstu.iu9.distributed.lab4.TestResult;

import java.util.HashMap;
import java.util.Map;

public class ResultsActor extends AbstractActor {
    private final Map<String, TestResult> testResults = new HashMap<>();


    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                ResultMessage.class, resultMessage ->
                        testResults.put(resultMessage.getPackageId(), resultMessage.getResult())
        ).match(
                RetrieveMessage.class, request ->
                        sender().tell(new ResultMessage(request.getPackageId(), testResults.get(request.getPackageId()), false),
                        self())
        ).build();
    }
}
