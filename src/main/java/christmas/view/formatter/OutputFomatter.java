package christmas.view.formatter;

import christmas.domain.AmountCalculator;
import christmas.domain.DiscountSummary;
import christmas.domain.Event;
import christmas.domain.FreeGift;
import christmas.domain.Order;
import christmas.domain.OrderGroup;
import christmas.domain.VisitDate;
import christmas.common.Symbol;
import java.util.Map.Entry;

public class OutputFomatter {

    public static final String MINUS = Symbol.HYPHEN;

    public int toDate(VisitDate date) {
        return date.getDate();
    }

    public String toMenu(Order order) {
        return order.getMenu();
    }

    public int toMenuCount(Order order) {
        return order.getCount();
    }

    public String toOrderAmount(OrderGroup orderGroup) {
        return formatMoney(orderGroup.calculateAmount());
    }

    public String toFreeGift(OrderGroup orderGroup) {
        FreeGift gift = orderGroup.getFreeGift();

        if (gift == FreeGift.NOTHING) {
            return "없음";
        }

        return String.format("%s 1개", gift.getName());
    }

    public String toFreeGiftPrice(DiscountSummary discountSummary) {
        return MINUS + formatMoney(discountSummary.getFreeGiftPrice());
    }

    public String toEventName(Entry<Event, Integer> eachEvent) {
        Event event = eachEvent.getKey();
        return event.getName();
    }

    public String toDiscount(Entry<Event, Integer> eachEvent) {
        return MINUS + formatMoney(eachEvent.getValue());
    }

    public String toTotalDiscount(DiscountSummary discountSummary) {
        String sign = "";
        if (discountSummary.hasDiscount()) {
            sign = MINUS;
        }
        return sign + formatMoney(discountSummary.getDiscountWithGift());
    }

    public String toFinalAmount(OrderGroup orderGroup, DiscountSummary discountSummary) {
        int finalAmount = AmountCalculator.calculateFinalAmount(orderGroup, discountSummary);
        return formatMoney(finalAmount);
    }

    public String toBadge(DiscountSummary discountSummary) {
        return discountSummary.getBadge();
    }

    private String formatMoney(int amount) {
        return String.format("%,d", amount);
    }
}
