package christmas.domain;

public class PurchaseAmount {
    private final int amount;

    public PurchaseAmount(int amount) {
        this.amount = amount;
    }

    public static PurchaseAmount from(OrderGroup orderGroup) {
        return new PurchaseAmount(orderGroup.calculateAmount());
    }

    public int getAmount() {
        return amount;
    }
}
