package ru.bmstu.iu9.distributed.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

// TODO rename
public class MainHttp {

    ActorSystem actorSystem;
    ActorRef routeActor;

    public MainHttp(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }
}
