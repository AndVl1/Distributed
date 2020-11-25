package ru.bmstu.iu9.distributed.lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.iu9.distributed.lab4.ResultMessage;
import ru.bmstu.iu9.distributed.lab4.TestMessage;
import ru.bmstu.iu9.distributed.lab4.TestRequest;
import ru.bmstu.iu9.distributed.lab4.TestsResults;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutorActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, testMessage -> {
                    System.out.println("Running " + testMessage.toString());
                    TestsResults result = runTests(testMessage) ;
                    sender().tell(new ResultMessage(testMessage.getPackageId(), result, true), self());
                })
                .build();
    }

    private TestsResults runTests(TestMessage message) throws Exception{
        ScriptEngine engine = new
                ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(message.getJsScript());

        Invocable invocable = (Invocable) engine;
        TestsResults results = new TestsResults();

        for(TestRequest.TestParams test : message.getTests()){
            String result = invocable.invokeFunction(message.getJsScript(), test.getParams()).toString();
            if (!result.equals(test.getExpectedResult())){
                results.addFailedTest(test.getTestName());
                System.out.println("TEST " + test.getTestName() + " FAILED\n"
                        + "expected: " + test.getExpectedResult() + " got result " + result);
            } else {
                results.addPassedTest(test.getTestName());
                System.out.println("TEST " + test.getTestName() + " PASSED");
            }
        }

        return results;
    }
}
