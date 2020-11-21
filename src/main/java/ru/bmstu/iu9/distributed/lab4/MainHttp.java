package ru.bmstu.iu9.distributed.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.server.Route;

import static akka.http.javadsl.server.Directives.route;

// TODO rename
public class MainHttp {

    private ActorSystem actorSystem;
    private ActorRef routeActor;

    public MainHttp(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        routeActor = actorSystem.actorOf(Props.create(RouterActor.class));
    }

    public Route getRoute() {
        return route()
    }
}
