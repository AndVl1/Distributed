package ru.bmstu.iu9.distributed.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import ru.bmstu.iu9.distributed.CsvUtils;

public class DelaysAppSpark {

    public static final String AIRPORTS_FILE_PATH = "airports.csv";
    public static final String FLIGHTS_FILE_PATH = "flights.csv";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Delays");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rawAirport = CsvUtils.getRddData(sc.textFile(AIRPORTS_FILE_PATH));
        JavaRDD<String> rawFlights = CsvUtils.getRddData(sc.textFile(FLIGHTS_FILE_PATH));

        

        final String line = rawAirport.first();
        rawAirport = rawFlights.filter(s -> !s.equals(line));


    }
}
