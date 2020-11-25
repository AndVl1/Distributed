package ru.bmstu.iu9.distributed.lab4;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class JsTestsApp {

    public static final String HOST = "localhost";
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("JsTestsSystem");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        MainHttp instance = new MainHttp(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                instance.getRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow, ConnectHttp.toHost(HOST, PORT), materializer);
        System.out.println("Server online at http://"+HOST+":"+PORT+"/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }
}
