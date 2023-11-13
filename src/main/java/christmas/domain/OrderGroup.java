package christmas.domain;

import christmas.domain.validator.OrderGroupValidator;
import christmas.util.Converter;
import christmas.view.validator.InputValidator;
import java.util.ArrayList;
import java.util.List;

public class OrderGroup {
    private final List<Order> orderGroup;

    public OrderGroup(List<Order> orderGroup) {
        this.orderGroup = orderGroup;
    }

    public static OrderGroup from(List<String> rawOrderGroup) {
        List<Order> orderGroup = convertOrderGroup(rawOrderGroup);
        OrderGroupValidator.validate(orderGroup);
        return new OrderGroup(orderGroup);
    }

    private static List<Order> convertOrderGroup(List<String> rawOrderGroup) {
        List<Order> orderGroup = new ArrayList<>();
        for (String rawOrder : rawOrderGroup) {
            String menu = Converter.getSplittedValue(InputValidator.MENU_AND_COUNT_SEPARATOR, 0, rawOrder);
            String count = Converter.getSplittedValue(InputValidator.MENU_AND_COUNT_SEPARATOR, 1, rawOrder);
            Order order = new Order(Menu.from(menu), MenuCount.from(Converter.convertToInt(count)));
            orderGroup.add(order);
        }
        return orderGroup;
    }

    public int calculateAmount() {
        int amount = 0;
        for (Order order : orderGroup) {
            amount = order.sumAmountWith(amount);
        }
        return amount;
    }

    public int getCountByCategory(VisitDate date, Event event) {
        return orderGroup.stream()
                .mapToInt(order -> order.getEventAvailableCount(date, event))
                .sum();
    }

    public boolean canApplyEvent() {
        return calculateAmount() >= 10000;
    }

    public FreeGift getFreeGift() {
        return FreeGift.from(calculateAmount());
    }
}
