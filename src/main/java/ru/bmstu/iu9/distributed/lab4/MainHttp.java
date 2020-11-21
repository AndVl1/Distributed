package ru.bmstu.iu9.distributed.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Route;

import static akka.http.javadsl.server.Directives.*;

// TODO rename
public class MainHttp {

    private ActorSystem actorSystem;
    private ActorRef routeActor;

    public MainHttp(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        routeActor = actorSystem.actorOf(Props.create(RouterActor.class));
    }

    public Route getRoute() {
        return route(
                path("execute", () ->
                        route(post(() ->
                                entity(Jackson.unmarshaller(TestRequest.class), body -> {
                                    routeActor.tell(new TestMessage(
                                            body.getPackageId(),
                                            body.getJsString(),
                                            body.getFunctionName(),
                                            body.getTests()), ActorRef.noSender());
                                    return complete(StatusCodes.OK, body.getPackageId());
                                }))))
        )
    }
}