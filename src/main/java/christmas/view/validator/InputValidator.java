package christmas.view.validator;

import christmas.common.ErrorMessage;
import christmas.util.Converter;
import christmas.util.Symbol;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    public static final String ORDER_SEPARATOR = Symbol.COMMA;
    public static final String MENU_AND_COUNT_SEPARATOR = Symbol.HYPHEN;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");
    private static InputValidator inputValidator;

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

    public void validateDate(String rawDate) {
        validateBlank(rawDate, ErrorMessage.DATE_IS_INVALID);
        validateNumeric(rawDate, ErrorMessage.DATE_IS_INVALID);
        validateIntegerRange(rawDate, ErrorMessage.DATE_IS_INVALID);
    }

    public void validateOrderGroup(String orderGroup) {
        validateBlank(orderGroup, ErrorMessage.ORDER_IS_INVALID);
        validateValidSeperator(ORDER_SEPARATOR, orderGroup, ErrorMessage.ORDER_IS_INVALID);
    }

    public void validateOrder(List<String> orderGroup) {
        orderGroup.forEach(order -> validateContains(MENU_AND_COUNT_SEPARATOR, order, ErrorMessage.ORDER_IS_INVALID));
        orderGroup.forEach(
                order -> validateValidSeperator(MENU_AND_COUNT_SEPARATOR, order, ErrorMessage.ORDER_IS_INVALID));
    }

    public void validateMenuCount(List<String> orderGroup) {
        for (String order : orderGroup) {
            String count = Converter.splitValue(MENU_AND_COUNT_SEPARATOR, 1, order);
            validateNumeric(count, ErrorMessage.ORDER_IS_INVALID);
            validateIntegerRange(count, ErrorMessage.ORDER_IS_INVALID);
        }
    }

    private void validateValidSeperator(String separator, String value, String message) {
        validateDoubleSeperator(separator, value, message);
        validateStartWord(separator, value, message);
        validateEndWord(separator, value, message);
    }


    private void validateBlank(String value, String message) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateNumeric(String value, String message) {
        if (!isNumber(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateIntegerRange(String value, String message) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateContains(String substring, String value, String message) {
        if (!value.contains(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateDoubleSeperator(String separator, String value, String message) {
        if (containsDoubleSeperator(separator, value)) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateStartWord(String separator, String value, String message) {
        if (value.startsWith(separator)) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateEndWord(String separator, String value, String message) {
        if (value.endsWith(separator)) {
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isNumber(String value) {
        return NUMBER_PATTERN.matcher(value).matches();
    }

    private boolean containsDoubleSeperator(String separator, String value) {
        String doubleSeperator = separator.repeat(2);
        return value.contains(doubleSeperator);
    }
}
