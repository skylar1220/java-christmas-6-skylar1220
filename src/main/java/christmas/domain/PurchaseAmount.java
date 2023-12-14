package christmas.domain;

public class PurchaseAmount {
    private final int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public boolean isOverOrEqual(int input) {
        return purchaseAmount >= input;
    }

    public boolean isOver(int input) {
        return purchaseAmount >= 10000;
    }

    public int getDifferenceWith(TotalBenefitAmout totalBenefitAmout) {
        return totalBenefitAmout.getDifferenceWith(purchaseAmount);
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
