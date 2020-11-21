package ru.bmstu.iu9.distributed.lab4;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

public class JsTestsApp {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("JsTestsSystem");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        // instance
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
    }
}
