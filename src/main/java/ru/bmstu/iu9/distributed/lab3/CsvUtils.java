package ru.bmstu.iu9.distributed.lab3;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import ru.bmstu.iu9.distributed.AirportHelper;
import scala.Tuple2;

public class CsvUtils {
    public static JavaRDD<String> getRddData(JavaRDD<String> data) {
        return removeCsvTitleString(data);
    }

    public static JavaRDD<FlightData> getFlightsRdd(JavaRDD<String> flights){
        return flights
                .map(line -> line.split(CSV_DELIMITER))
                .map(flightData -> {
                    if(isCanceled(flightData[CANCELLATION_INDEX])) {
                        return new FlightData(flightData[ORIGIN_AIRPORT_INDEX], flightData[DESTINATION_AIRPORT_INDEX]);
                    }
                    double delay = getDelay(flightData[DELAY_INDEX]);
                    return new FlightData(flightData[ORIGIN_AIRPORT_INDEX],
                            flightData[DESTINATION_AIRPORT_INDEX],
                            delay
                    );
                });
    }

    public static JavaPairRDD<String, AirportData> getAirportsPairRdd(JavaRDD<String> airports){
        return airports.map(line -> {
            AirportHelper codeAndName = AirportHelper.trimCodeAndName(line, CSV_DELIMITER);
            return new String[]{Integer.toString(codeAndName.getCode()), codeAndName.getDescription()};
        }).mapToPair(airportData -> {
            String id = airportData[0];
            String description = airportData[1];
            return new Tuple2<>(id, new AirportData(Integer.parseInt(id), description));
        });
    }

    // соответствие между аэропортами отправления/прибытия и информацией о полёте
    public static JavaPairRDD<Tuple2<String, String>, FlightData> getFlightsPairRdd(JavaRDD<FlightData> flights){
        return flights.mapToPair(flight -> new Tuple2<>(
                new Tuple2<>(flight.getOriginId(), flight.getDestinationId()),
                flight
                )
        );
    }

//    private static JavaRDD<String[]> splitAirportCsvLine(String line){
//        int firstComma = line.indexOf(CSV_DELIMITER);
//
//        String codeString = removeSpecSymbols(
//                line.substring(0, firstComma)
//        );
//        String description = line.substring(firstComma + 1).replaceAll(CSV_STRING_SYMBOL, "");
//
//        String[] codeAndName = {codeString, description};
//        return JavaRDD.toRDD(codeAndName);
//    }

    private static double getDelay(String delay) {
        return delay.isEmpty() ? NO_DELAY_TIME : Double.parseDouble(delay);
    }

    private static boolean isCanceled(String code) {
        return code.equals(CANCELLED_CODE);
    }

    private static JavaRDD<String> removeCsvTitleString(JavaRDD<String> data){
        String firstCsvLine = data.first();
        return data.filter(string -> !string.equals(firstCsvLine));
    }

    private final static double NO_DELAY_TIME = 0.00;
    private final static String CANCELLED_CODE = "1.00";
    private final static String CSV_DELIMITER = ",";

    private final static int ORIGIN_AIRPORT_INDEX = 11;
    private final static int DESTINATION_AIRPORT_INDEX = 14;
    private final static int DELAY_INDEX = 18;
    private final static int CANCELLATION_INDEX = 19;
}
