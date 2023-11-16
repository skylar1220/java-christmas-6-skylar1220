package christmas.util;

import java.util.Arrays;
import java.util.List;

public class Converter {
    private Converter() {
    }

    public static int convertToInt(String input) {
        return Integer.parseInt(input);
    }

    public static List<String> splitToList(String separator, String value) {
        return Arrays.asList(value.split(separator));
    }

    public static String splitValue(String separator, int index, String value) {
        return splitToList(separator, value).get(index);
    }
}
