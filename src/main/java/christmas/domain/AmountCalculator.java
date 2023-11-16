package christmas.domain;

public class AmountCalculator {
    private AmountCalculator() {
    }

    public static int calculateFinalAmount(OrderGroup orderGroup, DiscountSummary discountSummary) {
        return orderGroup.calculateAmount() - discountSummary.getDiscountBeforeGift();
    }
}
