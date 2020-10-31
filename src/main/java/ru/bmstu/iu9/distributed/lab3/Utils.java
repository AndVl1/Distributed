package ru.bmstu.iu9.distributed;

import org.apache.spark.api.java.JavaRDD;

public class CsvUtils {
    public static JavaRDD<String> getRddData(JavaRDD<String> data) {
        return removeCsvTitleString(data);
    }

    private static JavaRDD<String> removeCsvTitleString(JavaRDD<String> data){
        String firstCsvLine = data.first();
        return data.filter(string -> !string.equals(firstCsvLine));
    }
}
