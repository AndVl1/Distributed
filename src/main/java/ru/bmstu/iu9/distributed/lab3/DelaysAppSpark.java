package ru.bmstu.iu9.distributed.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class DelaysAppSpark {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Delays");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rawAirport=sc.textFile("airports.csv");
        JavaRDD<String> rawFlights=sc.textFile("flights.csv");

        final String line=rawAirport.first();
        rawAirport=rawFlights.filter(s->!s.equals(line));

        
    }
}
