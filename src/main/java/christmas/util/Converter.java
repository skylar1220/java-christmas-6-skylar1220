package christmas.util;

import java.util.Arrays;
import java.util.List;

public class Converter {
    public static int convertToInt(String input) {
        return Integer.parseInt(input);
    }

    public static List<String> split(String seperator, String value) {
        return Arrays.asList(value.split(seperator));
    }

    public static String getSplittedValue(String seperator, int index, String value) {
        return split(seperator, value).get(index);
    }
}
