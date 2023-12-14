package christmas.domain;

public class PurchaseAmount {
    private final int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public boolean isOverOrEqual(int input) {
        return purchaseAmount >= input;
    }
}
