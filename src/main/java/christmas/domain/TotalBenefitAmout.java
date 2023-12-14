package christmas.domain;

public class TotalBenefitAmout {

    private final int totalBenefitAmount;

    public TotalBenefitAmout(int totalBenefitAmount){
        this.totalBenefitAmount = totalBenefitAmount;
    }

    public static TotalBenefitAmout of(EventBenefitSummary eventBenefitSummary, FreeGift freeGift) {
        return new TotalBenefitAmout(eventBenefitSummary.calculateTotalBenefitAmout() + freeGift.getMenu().getPrice());
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public int getDifferenceWith(int purchaseAmount) {
        return purchaseAmount - totalBenefitAmount;
    }
}
