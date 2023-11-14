package christmas.domain.validator;

import christmas.common.ErrorMessage;

public class MenuCountValidator {

    public static final int MIN_MENU_COUNT = 1;

    public static void validate(int count) {
        validateRange(count);
    }

    private static void validateRange(int count) {
        if (!isInRange(count)){
            throw new IllegalArgumentException(ErrorMessage.ORDER_IS_INVALID);
        }
    }

    private static boolean isInRange(int count) {
        return count >= MIN_MENU_COUNT;
    }
}
