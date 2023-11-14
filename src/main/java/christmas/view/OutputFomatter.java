package christmas.view;

import christmas.domain.Order;
import christmas.domain.OrderGroup;
import christmas.domain.VisitDate;

public class OutputFomatter {
    public int toDate(VisitDate date) {
        return date.getDate();
    }

    public String toMenu(Order order) {
        return order.getMenuName();
    }

    public int toMenuCount(Order order) {
        return order.getCountNumber();
    }

    public String toPurchaseAmount(OrderGroup orderGroup) {
        return String.format("%,d", orderGroup.calculateAmount());
    }

    public String toFreeGift(OrderGroup orderGroup) {
        return orderGroup.getFreeGift().name();
    }
}
