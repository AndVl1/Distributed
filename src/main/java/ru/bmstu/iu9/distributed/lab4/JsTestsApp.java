package ru.bmstu.iu9.distributed.lab4;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;

public class JsTestsApp {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("JsTestsSystem");
        final Http http = Http.get(system);
        
    }
}
