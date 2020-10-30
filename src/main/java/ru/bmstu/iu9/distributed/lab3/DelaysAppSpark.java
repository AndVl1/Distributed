package ru.bmstu.iu9.distributed.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class DelaysAppSpark {
    SparkConf conf = new SparkConf().setAppName("Delays");
    JavaSparkContext sc = new JavaSparkContext(conf);
}
