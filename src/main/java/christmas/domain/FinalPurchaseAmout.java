package christmas.domain;

public class FinalPurchaseAmout {
    private final int finalPurchaseAmount;

    public FinalPurchaseAmout(int finalPurchaseAmount) {
        this.finalPurchaseAmount = finalPurchaseAmount;
    }

    public static FinalPurchaseAmout of(PurchaseAmount purchaseAmount, TotalBenefitAmout totalBenefitAmout) {
        return  new FinalPurchaseAmout(purchaseAmount.getDifferenceWith(totalBenefitAmout));
    }

    public boolean isOverOrEqual(int minBenefitAmount) {
        return finalPurchaseAmount >- minBenefitAmount;
    }

    public int getFinalPurchaseAmount() {
        return finalPurchaseAmount;
    }
}
