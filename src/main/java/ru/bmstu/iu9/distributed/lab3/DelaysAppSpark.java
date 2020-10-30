package ru.bmstu.iu9.distributed.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class DelaysAppSpark {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Delays");
        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.textFile("");
    }
}
