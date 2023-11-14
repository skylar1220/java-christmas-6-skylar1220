package christmas.domain;

public class Amount {
    private final int amount;

    public Amount(int amount) {
        this.amount = amount;
    }

    public static Amount of(OrderGroup orderGroup, DiscountSummary discountSummary) {
        return new Amount(calculateAmount(orderGroup, discountSummary));
    }

    private static int calculateAmount(OrderGroup orderGroup, DiscountSummary discountSummary) {
        return orderGroup.calculateAmount() - discountSummary.getDiscountBeforeGift();
    }

    public int getAmount() {
        return amount;
    }
}
