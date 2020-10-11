package ru.bmstu.iu9.distributed;

public class StringUtils {

    // Trimming simple csv, without delimiter symbols in fields
    // example: "aaa", "bbb"
    // not "aa, bb", "cc, dd"
    public static String[] trimSimpleCsv(String line, String delimiter) {
        return line.split(delimiter);
    }

    // Trim csv ***, "name"; where *** is integer code
    public static AirportHelper trimCodeAndName(String line, char delimiter) {
        int firstComma = 0;
        for (char symbol: line.toCharArray()){
            if (symbol == delimiter){
                break;
            }
            firstComma++;
        }
        int code;
        try {
            String codeString = line
                    .substring(0, firstComma)
                    .replaceAll(SPEC_SYMBOLS, "");
            code = Integer.parseInt(codeString);
        } catch (Exception e){
            // This is header, so we must just continue to next line
            e.printStackTrace();
            return null;
        }
        String description = line.substring(firstComma + 1).replace('"', '\0');
        AirportHelper result = new AirportHelper();
        result.setCode(code);
        result.setDescription(description);
        return result;
    }

    private static class AirportHelper {
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
    }

    private final static String SPEC_SYMBOLS = "[\"\\s+]";
}
