package christmas.util;

import christmas.domain.DiscountSummary;
import christmas.domain.OrderGroup;

public class AmountCalculator {
    private AmountCalculator() {
    }

    public static int calculateFinalAmount(OrderGroup orderGroup, DiscountSummary discountSummary) {
        return orderGroup.calculateAmount() - discountSummary.getDiscountBeforeGift();
    }
}
