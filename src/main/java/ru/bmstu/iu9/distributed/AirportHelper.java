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
        int firstComma = line.indexOf(delimiter);
        int code;

        String codeString = removeSpecSymbols(
                line.substring(0, firstComma)
        );
        code = Integer.parseInt(codeString);

        String description = line.substring(firstComma + 1).replaceAll(CSV_STRING_SYMBOL, "");
        AirportHelper result = new AirportHelper();
        result.setCode(code);
        result.setDescription(description);
        return result;
    }

}
