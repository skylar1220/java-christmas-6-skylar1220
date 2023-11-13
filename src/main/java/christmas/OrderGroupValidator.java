package christmas;

import java.util.List;

public class OrderGroupValidator {
    public static final String ORDER_GROUP_CONTAIN_ONLY_DRINK = "음료만 주문할 수는 없습니다. 다시 입력해주세요";

    public static void validate(List<Order> orderGroup) {
        validateTotalCount(orderGroup);
        validateDuplicates(orderGroup);
        validateCategoryOnly(MenuCategory.DRINK, orderGroup);
    }

    private static void validateCategoryOnly(MenuCategory category, List<Order> orderGroup) {
        if (hasCategoryOnly(category, orderGroup)) {
            throw new IllegalArgumentException(ORDER_GROUP_CONTAIN_ONLY_DRINK);
        }

    }

    private static boolean hasCategoryOnly(MenuCategory category, List<Order> orderGroup) {
        return orderGroup.stream()
                .filter(order -> !order.isMenuCategory(category))
                .findAny()
                .isEmpty();
    }

    private static void validateDuplicates(List<Order> orderGroup) {
        if (hasDuplicates(orderGroup)) {
            throw new IllegalArgumentException(InputValidator.ORDER_IS_INVALID);
        }
    }

    private static boolean hasDuplicates(List<Order> orderGroup) {
        return orderGroup.stream()
                .anyMatch(order -> order.hasSameMenu(orderGroup));
    }

    private static void validateTotalCount(List<Order> orderGroup) {
        int count = 0;
        for (Order order : orderGroup) {
            count = order.sumCountWith(count);
        }
        if (!isInRage(count)) {
            throw new IllegalArgumentException(InputValidator.ORDER_IS_INVALID);
        }
    }

    private static boolean isInRage(int count) {
        return count >= 1 && count <= 20;
    }

}
