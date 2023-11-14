package christmas.view;

import christmas.domain.Amount;
import christmas.domain.DiscountSummary;
import christmas.domain.Event;
import christmas.domain.FreeGift;
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

    public String toOrderAmount(OrderGroup orderGroup) {
        return String.format("%,d", orderGroup.calculateAmount());
    }

    public String toFreeGift(OrderGroup orderGroup) {
        FreeGift gift = orderGroup.getFreeGift();
        if (gift == FreeGift.NOTHING) {
            return gift.getName();
        }
        return String.format("%s 1개", gift.getName());
    }

    public String toFreeGiftPrice(DiscountSummary discountSummary) {
        return String.format("%,d", discountSummary.getFreeGiftPrice());
    }

    public String toEventName(Entry<Event, Integer> eachDiscountSummary) {
        return eachDiscountSummary.getKey().getName();
    }

    public String toDiscount(Entry<Event, Integer> eachDiscountSummary) {
        return String.format("-%,d", eachDiscountSummary.getValue());
    }

    public String toTotalDiscount(DiscountSummary discountSummary) {
        String minus = "";
        if (discountSummary.hasDiscount()) {
            minus = "-";
        }
        return String.format(minus + "%,d", discountSummary.getDiscountWithGift());
    }

    public String toFianlAmount(Amount amount) {
        return String.format("%,d", amount.getAmount());
    }

    public String toBadge(DiscountSummary discountSummary) {
        return discountSummary.getBadgeName();
    }
}
