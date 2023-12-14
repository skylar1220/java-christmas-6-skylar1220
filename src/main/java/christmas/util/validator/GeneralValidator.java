package christmas.util.validator;

import christmas.util.converter.Converter;
import java.util.List;
import java.util.Set;

public class GeneralValidator {
    private GeneralValidator() {
    }

    public static void validateDuplicateSubstring(String substring, String value, String message) {
        if (containsDuplicateSubstring(substring, value)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateStartSubstring(String substring, String value, String message) {
        if (value.startsWith(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateEndSubstring(String substring, String value, String message) {
        if (value.endsWith(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateDuplicateValues(List<String> values, String message) {
        if (hasDuplicates(values)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateSplittedCount(String seperator, String value, int requiredCount, String message) {
        List<String> values = Converter.splitToList(seperator, value);
        if (!hasValidCount(values, requiredCount)) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void validateCount(List<String> values, int requiredCount, String message) {
        if (hasValidCount(values, requiredCount)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean hasValidCount(List<String> values, int requiredCount) {
        return values.size() == requiredCount;
    }

    private static boolean containsDuplicateSubstring(String substring, String value) {
        String doubleSubstring = substring.repeat(2);
        return value.contains(doubleSubstring);
    }

    private static boolean hasDuplicates(List<String> values) {
        return Set.copyOf(values).size() != values.size();
    }
}
