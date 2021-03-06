package ru.bmstu.iu9.distributed.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.util.LongAccumulator;
import scala.Tuple2;

import java.util.Iterator;
import java.util.Map;

public class DelaysAppSpark {

    public static final String AIRPORTS_FILE_PATH = "airports.csv";
    public static final String FLIGHTS_FILE_PATH = "flights.csv";
    public static final String OUTPUT_PATH = "output";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Delays");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        // убираю заголовки (названия колонок)
        JavaRDD<String> rawAirport = CsvUtils.getRddData(sparkContext.textFile(AIRPORTS_FILE_PATH));
        JavaRDD<String> rawFlights = CsvUtils.getRddData(sparkContext.textFile(FLIGHTS_FILE_PATH));

        // пара (airportId | data)
        JavaPairRDD<String, AirportData> airportsPairRdd = CsvUtils.getAirportsPairRdd(rawAirport);
        // информация о полёте
        JavaRDD<FlightData> flightsRdd = CsvUtils.getFlightsRdd(rawFlights);
        // (flights origin and destination ids) - (flight data)
        JavaPairRDD<Tuple2<String, String>, FlightData> flightIdsToDataAccordance = CsvUtils.getFlightIdsToDataAccordance(flightsRdd);
        // data._1.**

        final Broadcast<Map<String, AirportData>> airportBroadcast = sparkContext.broadcast(airportsPairRdd.collectAsMap());

        flightIdsToDataAccordance.reduceByKey(DelaysAppSpark::getAccumulatedData)
                .mapToPair(data -> {
                    AirportData originAirport = airportBroadcast.getValue().get(data._1()._1());
                    AirportData destinationAirport = airportBroadcast.getValue().get(data._1()._2());
                    return new Tuple2<>(new Tuple2<>(originAirport, destinationAirport), data._2());
        }).saveAsTextFile(OUTPUT_PATH);
    }

    private static FlightData getAccumulatedData(FlightData a, FlightData b) {
        double newDelay = 0d;
        if (a.isDelayed() || b.isDelayed()) {
            newDelay = Math.max(a.getDelay(), b.getDelay());
        }
        return new FlightData(a.getOriginId(), a.getDestinationId(), 1,
                newDelay, a.getDelayedOrCancelledCount() + b.getDelayedOrCancelledCount(),
                a.getTotalCount() + b.getTotalCount());
    }
}
