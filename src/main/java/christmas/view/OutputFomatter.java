package christmas.view;

import christmas.domain.DiscountSummary;
import christmas.domain.Event;
import christmas.domain.Order;
import christmas.domain.OrderGroup;
import christmas.domain.VisitDate;
import java.util.Map.Entry;

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

    public String toEvent(Entry<Event, Integer> eachDiscountSummary) {
        return eachDiscountSummary.getKey().getName();
    }

    public String toDiscount(Entry<Event, Integer> eachDiscountSummary) {
        return String.format("%,d", eachDiscountSummary.getValue());
    }

    public String toFreeGiftPrice(DiscountSummary discountSummary) {
        return String.format("%,d", discountSummary.getFreeGiftPrice());
    }

    public String toTotalDiscount(DiscountSummary discountSummary) {
        return String.format("%,d", discountSummary.getDiscountWithGift());
    }
}
