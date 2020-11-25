package ru.bmstu.iu9.distributed.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.util.Timeout;
import ru.bmstu.iu9.distributed.lab4.actors.RouterActor;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

// TODO rename
public class MainHttp {

    public static final Timeout TIMEOUT = Timeout.create(Duration.ofSeconds(3));
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
                                            body.getJsScript(),
                                            body.getFunctionName(),
                                            body.getTests()), ActorRef.noSender());
                                    return complete(StatusCodes.OK, body.getPackageId());
                                })))),
                path("get", () ->
                        route(get(() ->
                                parameter("packageId", packageId -> {
                                    Future<Object> future = Patterns.ask(routeActor, new RetrieveMessage(packageId),
                                            TIMEOUT);
                                    ResultMessage resultMessage;
                                    try {
                                        resultMessage = (ResultMessage) Await.result(future, TIMEOUT.duration());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return complete(StatusCodes.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
                                    }
                                    if (resultMessage != null && resultMessage.getResult() != null){
                                        return complete(StatusCodes.OK, resultMessage.getResult().toString());
                                    } else {
                                        return complete(StatusCodes.OK, "")
                                    }
                                }))))
        );
    }
}
