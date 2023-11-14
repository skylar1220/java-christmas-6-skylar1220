package christmas.view;

import christmas.domain.Order;
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
}
