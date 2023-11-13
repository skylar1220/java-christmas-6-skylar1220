package christmas.domain.validator;

import christmas.view.validator.InputValidator;

public class VisitDateValidator {
    public static void validate(int date) {
        validateRange(date);
    }

    private static void validateRange(int date) {
        if (!isInRange(date)) {
            throw new IllegalArgumentException(InputValidator.DATE_IS_INVALID);
        }
    }

    private static boolean isInRange(int date) {
        return date >= 1 && date <= 31;
    }
}
