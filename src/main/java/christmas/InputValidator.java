package christmas;

import java.util.regex.Pattern;

public class InputValidator {
    public static final String DATE_IS_INVALID = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
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

    public void validateNull(String value, String message) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateNumeric(String value, String message) {
        if (!isNumber(value)) {
            throw new IllegalArgumentException(message);
        }
    }
    private boolean isNumber(String value) {
        return NUMBER_PATTERN.matcher(value).matches();
    }
    private void validateIntegerRange(String value, String message) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }
}
