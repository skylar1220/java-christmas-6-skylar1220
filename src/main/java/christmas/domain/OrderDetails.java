package christmas.domain;

import christmas.common.Symbol;
import christmas.util.converter.Converter;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
    public static final String MENU_AND_COUNT_SEPARATOR = Symbol.HYPHEN;
    private final List<OrderDetail> orderDetails;

    public OrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public static OrderDetails from(List<String> rawOrderDetails) {
        validateCount(rawOrderDetails);
        List<OrderDetail> orderDetails = convertToOrderDetails(rawOrderDetails);
        validateCategory(orderDetails);
        return new OrderDetails(orderDetails);
    }

    private static List<OrderDetail> convertToOrderDetails(List<String> rawOrderDetails) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        rawOrderDetails.forEach(orderDetail -> {
                    String menu = Converter.getSplittedValueOf(MENU_AND_COUNT_SEPARATOR, 0, orderDetail);
                    String menuCount = Converter.getSplittedValueOf(MENU_AND_COUNT_SEPARATOR, 1, orderDetail);
                    orderDetails.add(OrderDetail.of(menu, Converter.convertToInt(menuCount)));
                }
        );
        return orderDetails;
    }

    private static void validateCount(List<String> orderDetails) {
        if (!isValidCount(orderDetails)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

    }

    private static boolean isValidCount(List<String> orderDetails) {
        int countSum = orderDetails.stream()
                .mapToInt(orderDetail -> Converter.convertToInt(
                        Converter.getSplittedValueOf(MENU_AND_COUNT_SEPARATOR, 1, orderDetail)))
                .sum();
        return countSum >= 0 && countSum <= 20;
    }

    private static void validateCategory(List<OrderDetail> orderDetails) {
        if (isDrinksOnly(orderDetails)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean isDrinksOnly(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .allMatch(OrderDetail::isDrink);
    }
}
