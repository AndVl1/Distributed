package ru.bmstu.iu9.distributed.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.pattern.Patterns;
import akka.routing.BalancingPool;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.time.Duration;

public class RouterActor extends AbstractActor {

    private static final int POOL_SIZE = 3;
    public static final Timeout TIMEOUT = Timeout.create(Duration.ofSeconds(3));
    private ActorRef storageActor;
    private ActorRef executeActor;

    @Override
    public void preStart() {
        storageActor = getContext().actorOf(Props.create(ResultsActor.class));
        executeActor = getContext().actorOf(
                new BalancingPool(POOL_SIZE).props(Props.create(TestExecutorActor.class))
        );
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, msg -> executeActor.tell(msg, self()))
                .match(ResultsActor.class, msg -> storageActor.tell(msg, self()))
                .match(RetrieveMessage.class, msg -> {
                    Future<Object> future = Patterns.ask(storageActor, msg, TIMEOUT);
                    sender().tell(Await.result(future, TIMEOUT.duration()), self());
                }).build();
    }
}
