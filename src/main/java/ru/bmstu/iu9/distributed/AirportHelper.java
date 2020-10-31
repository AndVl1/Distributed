package ru.bmstu.iu9.distributed;

import static ru.bmstu.iu9.distributed.StringUtils.removeSpecSymbols;

public class AirportHelper {
    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static AirportHelper trimCodeAndName(String csvLine, char delimiter) {
        return getAirportHelper(csvLine, csvLine.indexOf(delimiter));
    }

    public static AirportHelper trimCodeAndName(String csvLine, String delimiter) {
        return getAirportHelper(csvLine, csvLine.indexOf(delimiter));
    }

    private static AirportHelper getAirportHelper(String csvLine, int firstIndexOfDelimiter) {
        int code;

        String codeString = removeSpecSymbols(
                csvLine.substring(0, firstIndexOfDelimiter)
        );
        code = Integer.parseInt(codeString);

        String description = removeSpecSymbols(csvLine.substring(firstIndexOfDelimiter + 1));

        AirportHelper result = new AirportHelper();
        result.setCode(code);
        result.setDescription(description);
        return result;
    }

}
