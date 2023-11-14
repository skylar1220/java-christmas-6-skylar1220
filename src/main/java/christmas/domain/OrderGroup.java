package christmas.domain;

import christmas.domain.validator.OrderGroupValidator;
import christmas.util.Converter;
import christmas.view.validator.InputValidator;
import java.util.List;

public class OrderGroup {
    private final List<Order> orderGroup;

    public OrderGroup(List<Order> orderGroup) {
        this.orderGroup = orderGroup;
    }

    public static OrderGroup from(List<String> rawOrderGroup) {
        List<Order> orderGroup = convertToOrderList(rawOrderGroup);
        OrderGroupValidator.validate(orderGroup);
        return new OrderGroup(orderGroup);
    }

    private static List<Order> convertToOrderList(List<String> rawOrderGroup) {
        return rawOrderGroup.stream()
                .map(OrderGroup::convertOrder)
                .toList();
    }

    private static Order convertOrder(String rawOrder) {
        String menu = Converter.splitValue(InputValidator.MENU_AND_COUNT_SEPARATOR, 0, rawOrder);
        String count = Converter.splitValue(InputValidator.MENU_AND_COUNT_SEPARATOR, 1, rawOrder);
        return new Order(Menu.from(menu), MenuCount.from(Converter.convertToInt(count)));
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
        return calculateAmount() >= MIN_EVENT_AMOUNT();
    }

    private static int MIN_EVENT_AMOUNT() {
        return 10000;
    }

    public FreeGift getFreeGift() {
        return FreeGift.from(calculateAmount());
    }

    public List<Order> getOrderGroup() {
        return orderGroup;
    }
}
