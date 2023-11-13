package christmas.domain.validator;

import christmas.view.validator.InputValidator;

public class MenuCountValidator {

    public static void validate(int count) {
        validateRange(count);
    }

    private static void validateRange(int count) {
        if (!isInRange(count)){
            throw new IllegalArgumentException(InputValidator.ORDER_IS_INVALID);
        }
    }

    private static boolean isInRange(int count) {
        return count >= 1;
    }
}
