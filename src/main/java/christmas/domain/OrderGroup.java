package christmas.domain;

import christmas.domain.validator.OrderGroupValidator;
import christmas.util.Converter;
import christmas.view.validator.InputValidator;
import java.util.Collections;
import java.util.List;

public class OrderGroup {
    public static final int MENU_NAME_INDEX = 0;
    public static final int MENU_COUNT_INDEX = 1;
    public static final int MIN_EVENT_AMOUNT = 10000;
    private final List<Order> orderGroup;

    private OrderGroup(List<Order> orderGroup) {
        this.orderGroup = orderGroup;
    }

    public static OrderGroup from(List<String> rawOrderGroup) {
        List<Order> orderGroup = convertToOrderList(rawOrderGroup);
        OrderGroupValidator.validate(orderGroup);
        
        return new OrderGroup(orderGroup);
    }

    public int getMenuCountByCategory(List<MenuCategory> categories) {
        return orderGroup.stream()
                .mapToInt(order -> order.getMenuCountByCategory(categories))
                .sum();
    }

    public boolean canApplyEvent() {
        return calculateAmount() >= MIN_EVENT_AMOUNT;
    }

    public FreeGift getFreeGift() {
        return FreeGift.from(calculateAmount());
    }

    public int calculateAmount() {
        int totalAmount = 0;
        for (Order order : orderGroup) {
            totalAmount = order.sumAmountWith(totalAmount);
        }
        return totalAmount;
    }

    public List<Order> getOrderGroup() {
        return Collections.unmodifiableList(orderGroup);
    }

    private static List<Order> convertToOrderList(List<String> rawOrderGroup) {
        return rawOrderGroup.stream()
                .map(OrderGroup::convertOrder)
                .toList();
    }

    private static Order convertOrder(String order) {
        String menu = Converter.splitValue(InputValidator.MENU_AND_COUNT_SEPARATOR, MENU_NAME_INDEX, order);
        String count = Converter.splitValue(InputValidator.MENU_AND_COUNT_SEPARATOR, MENU_COUNT_INDEX, order);
        return new Order(Menu.from(menu), MenuCount.from(Converter.convertToInt(count)));
    }
}
