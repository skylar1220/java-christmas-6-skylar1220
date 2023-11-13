package christmas.view.validator;

import christmas.util.Converter;
import christmas.util.Seperator;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {
    public static final String DATE_IS_INVALID = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String ORDER_IS_INVALID = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    //    private static final String ORDER_COUNT_IS_OVER = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.";
    public static final String ORDER_SEPARATOR = Seperator.COMMA;
    public static final String MENU_AND_COUNT_SEPARATOR = Seperator.HYPHEN;

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");
    private static InputValidator inputValidator;

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

    public void validateDate(String rawDate) {
        validateNull(rawDate, DATE_IS_INVALID);
        validateNumeric(rawDate, DATE_IS_INVALID);
        validateIntegerRange(rawDate, DATE_IS_INVALID);
    }

    public void validateOrderGroup(String orderGroup) {
        validateNull(orderGroup, ORDER_IS_INVALID);
        validateSeperator(ORDER_SEPARATOR, orderGroup, ORDER_IS_INVALID);
    }

    public void validateOrder(List<String> orderGroup) {
        orderGroup.forEach(
                order -> validateSeperator(MENU_AND_COUNT_SEPARATOR, order, ORDER_IS_INVALID));
    }

    public void validateOrderCount(List<String> orderGroup) {
        for (String order : orderGroup) {
            String count = Converter.getSplittedValue(MENU_AND_COUNT_SEPARATOR, 1, order);
            validateNumeric(count, ORDER_IS_INVALID);
            validateIntegerRange(count, ORDER_IS_INVALID);
        }
    }

    private void validateSeperator(String seperator, String value, String message) {
        validateDoubleSeperator(seperator, value, message);
        validateStartWord(seperator, value, message);
        validateEndWord(seperator, value, message);
    }

    private void validateNull(String value, String message) {
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

    private void validateDoubleSeperator(String seperator, String value, String message) {
        if (containsDoubleSeperator(seperator, value)) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateStartWord(String seperator, String value, String message) {
        if (value.startsWith(seperator)) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateEndWord(String seperator, String value, String message) {
        if (value.endsWith(seperator)) {
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isNumber(String value) {
        return NUMBER_PATTERN.matcher(value).matches();
    }

    private boolean containsDoubleSeperator(String seperator, String value) {
        String doubleSeperator = seperator.repeat(2);
        return value.contains(doubleSeperator);
    }
}
