package ru.bmstu.iu9.distributed.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Iterator;
import java.util.Map;

public class DelaysAppSpark {

    public static final String AIRPORTS_FILE_PATH = "airports.csv";
    public static final String FLIGHTS_FILE_PATH = "flights.csv";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Delays");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rawAirport = Utils.getRddData(sc.textFile(AIRPORTS_FILE_PATH));
        JavaRDD<String> rawFlights = Utils.getRddData(sc.textFile(FLIGHTS_FILE_PATH));

        JavaPairRDD<String, AirportData> airportsPairRdd = Utils.getAirportsPairRdd(rawAirport);
        JavaPairRDD<Tuple2<String, String>, FlightData> flightsRdd = Utils.getFlightsId(
                Utils.getFlightsRdd(rawFlights)
        );

        final Broadcast<Map<String, AirportData>> airportBroadcasted = sc.broadcast(airportsPairRdd.collectAsMap());

        flightsRdd.groupByKey()
                .mapValues(flight -> {
                    Iterator<FlightData> flightsIterator = flight.iterator();
                    double maxDelay = 0;
                    int delayedFlights = 0;
                    int cancelledFlights = 0;
                    int i = 0;
                    while (flightsIterator.hasNext()) {
                        i++;
                        FlightData currentFlight = flightsIterator.next();
                        if (currentFlight.isCancelled()) {
                            cancelledFlights++;
                        } else if (currentFlight.isDelayed()) {
                            maxDelay = Math.max(maxDelay, currentFlight.getDelay());
                            delayedFlights++;
                        }
                    }
                    
                })
                .map();

    }
}
