package ru.bmstu.iu9.distributed;

import static ru.bmstu.iu9.distributed.StringUtils.CSV_STRING_SYMBOL;
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

    public static AirportHelper trimCodeAndName(String line, char delimiter) {
        return getAirportHelper(line, line.indexOf(delimiter));
    }

    public static AirportHelper trimCodeAndName(String line, String delimiter) {
        return getAirportHelper(line, line.indexOf(delimiter));
    }

    private static AirportHelper getAirportHelper(String line, int firstIndexOfDelimiter) {
        int code;

        String codeString = removeSpecSymbols(
                line.substring(0, firstIndexOfDelimiter)
        );
        code = Integer.parseInt(codeString);

        String description = removeSpecSymbols(line.substring(firstIndexOfDelimiter + 1));

        AirportHelper result = new AirportHelper();
        result.setCode(code);
        result.setDescription(description);
        return result;
    }

}
