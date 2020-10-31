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
    public static final String OUTPUT_PATH = "output";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Delays");
        JavaSparkContext sc = new JavaSparkContext(conf);
        // убираю заголовки (названия колонок)
        JavaRDD<String> rawAirport = CsvUtils.getRddData(sc.textFile(AIRPORTS_FILE_PATH));
        JavaRDD<String> rawFlights = CsvUtils.getRddData(sc.textFile(FLIGHTS_FILE_PATH));

        // пара (airportId | data)
        JavaPairRDD<String, AirportData> airportsPairRdd = CsvUtils.getAirportsPairRdd(rawAirport);
        // информация о полёте
        JavaRDD<FlightData> flightsRdd1 = CsvUtils.getFlightsRdd(rawFlights);
        // accordance of (flights origin and destination ids) to (flight data)
        JavaPairRDD<Tuple2<String, String>, FlightData> flightIdsToDataAccordance = CsvUtils.getFlightIdsToDataAccordance(flightsRdd1);

        final Broadcast<Map<String, AirportData>> airportBroadcasted = sc.broadcast(airportsPairRdd.collectAsMap());

        flightIdsToDataAccordance.groupByKey()
                .mapValues(flight -> {
                    Iterator<FlightData> flightsIterator = flight.iterator();
                    double maxDelay = 0;
                    int delayedFlights = 0;
                    int cancelledFlights = 0;
                    int flightsCount = 0;
                    while (flightsIterator.hasNext()) {
                        flightsCount++;
                        FlightData currentFlight = flightsIterator.next();
                        if (currentFlight.isCancelled()) {
                            cancelledFlights++;
                        } else if (currentFlight.isDelayed()) {
                            maxDelay = Math.max(maxDelay, currentFlight.getDelay());
                            delayedFlights++;
                        }
                    }
                    double delayedFlightsPercent = (double) delayedFlights / (double) flightsCount * 100;
                    double cancelledFlightsPercent = (double) cancelledFlights / (double) flightsCount * 100;
                    return new Tuple2<>(maxDelay, delayedFlightsPercent + cancelledFlightsPercent);
                })
                .map(data -> {
                    AirportData originAirport = airportBroadcasted.getValue().get(data._1()._1());
                    AirportData destinationAirport = airportBroadcasted.getValue().get(data._1()._2());
                    return new Tuple2<>(new Tuple2<>(originAirport, destinationAirport), data._2());
                }).saveAsTextFile(OUTPUT_PATH);
    }
}
