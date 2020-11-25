package ru.bmstu.iu9.distributed.lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.iu9.distributed.lab4.TestMessage;
import ru.bmstu.iu9.distributed.lab4.TestRequest;
import ru.bmstu.iu9.distributed.lab4.TestResult;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

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
        ScriptEngine engine = new
                ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(message.getJsScript());

        Invocable invocable = (Invocable) engine;
        TestResult testResult = new TestResult();

        for(TestRequest.TestParams test : message.getTests()){

        }

        return invocable.invokeFunction(functionName, params).toString();
    }
}
