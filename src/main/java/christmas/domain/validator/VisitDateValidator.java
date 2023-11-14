package christmas.domain.validator;

import christmas.common.ErrorMessage;

public class VisitDateValidator {
    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;

    public static void validate(int date) {
        validateRange(date);
    }

    private static void validateRange(int date) {
        if (!isInRange(date)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_IS_INVALID);
        }
    }

    private static boolean isInRange(int date) {
        return date >= MIN_DATE && date <= MAX_DATE;
    }
}
