package christmas.domain.validator;

import christmas.common.ErrorMessage;
import christmas.domain.MenuCategory;
import christmas.domain.Order;
import java.util.List;

public class OrderGroupValidator {
    private OrderGroupValidator() {
    }

    public static void validate(List<Order> orderGroup) {
        validateTotalCount(orderGroup);
        validateDuplicates(orderGroup);
        validateCategoryOnly(MenuCategory.DRINK, orderGroup);
    }

    private static void validateTotalCount(List<Order> orderGroup) {
        int count = getTotalCount(orderGroup);
        if (!isInRage(count)) {
            throw new IllegalArgumentException("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        }
    }

    private static void validateDuplicates(List<Order> orderGroup) {
        if (hasDuplicates(orderGroup)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_IS_INVALID);
        }
    }

    private static void validateCategoryOnly(MenuCategory category, List<Order> orderGroup) {
        if (hasCategoryOnly(category, orderGroup)) {
            throw new IllegalArgumentException(category.getName() + "만 주문할 수는 없습니다. 다시 입력해주세요");
        }

    }

    private static int getTotalCount(List<Order> orderGroup) {
        int count = 0;
        for (Order order : orderGroup) {
            count = order.addCount(count);
        }
        return count;
    }

    private static boolean isInRage(int count) {
        return count >= 1 && count <= 20;
    }

    private static boolean hasDuplicates(List<Order> orderGroup) {
        return orderGroup.stream()
                .anyMatch(order -> order.hasDuplicateMenu(orderGroup));
    }

    private static boolean hasCategoryOnly(MenuCategory category, List<Order> orderGroup) {
        return orderGroup.stream()
                .filter(order -> !order.isMenuCategory(category))
                .findAny()
                .isEmpty();
    }
}
