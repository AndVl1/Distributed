package ru.bmstu.iu9.distributed.lab3;

import org.apache.spark.api.java.JavaRDD;

public class Utils {
    public static JavaRDD<String> getRddData(JavaRDD<String> data) {
        return removeCsvTitleString(data);
    }

    public static JavaRDD<FlightData> getFlightsRdd(JavaRDD<String> flights){
        return flights
                .map(line -> line.split(CSV_DELIMITER))
                .map(flightData -> {
                    if(isCanceled(flightData[CANCELLATION_INDEX])) {
                        return new FlightData(flightData[ORIGIN_AIRPORT_INDEX],flightData[DESTINATION_AIRPORT_INDEX]);
                    }
                    String delay = flightData[DELAY_INDEX].isEmpty() ? NO_DELAY_TIME : flightData[DELAY_INDEX];
                    return new FlightData(flightData[ORIGIN_AIRPORT_INDEX],
                            flightData[DESTINATION_AIRPORT_INDEX],
                            Double.parseDouble(delay)
                    );
                });
    }

    private static boolean isCanceled(String code) {
        return code.equals(CANCELLED_CODE);
    }

    private static JavaRDD<String> removeCsvTitleString(JavaRDD<String> data){
        String firstCsvLine = data.first();
        return data.filter(string -> !string.equals(firstCsvLine));
    }

    private final static String NO_DELAY_TIME = "0.00";
    private final static String CANCELLED_CODE = "1.00";
    private final static String CSV_DELIMITER = ",";

    private final static int ORIGIN_AIRPORT_INDEX = 11;
    private final static int DESTINATION_AIRPORT_INDEX = 14;
    private final static int DELAY_INDEX = 18;
    private final static int CANCELLATION_INDEX = 19;
}
