package ru.bmstu.iu9.distributed.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class RouterActor extends AbstractActor {

    private ActorRef storageActor;
    private ActorRef executeActor;

    @Override
    public void preStart() {
        storageActor = getContext().actorOf(Props.create())
    }

    @Override
    public Receive createReceive() {

    }
}
