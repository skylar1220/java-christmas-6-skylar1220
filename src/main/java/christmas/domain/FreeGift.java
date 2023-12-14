package christmas.domain;

import java.util.Arrays;

public enum FreeGift {
    CHAMPAIGN(Menu.CHAMPAGNE, 120_000),
    NOTHING(Menu.CHAMPAGNE, 0);

    private final Menu menu;
    private final int minPurchaseAmount;

    FreeGift(Menu menu, int minPurchaseAmount) {
        this.menu = menu;
        this.minPurchaseAmount = minPurchaseAmount;
    }
    public static FreeGift from(PurchaseAmount input) {
        return Arrays.stream(values())
                .filter(gift ->  input.isOverOrEqual(gift.minPurchaseAmount))
                .findAny()
                .orElse(NOTHING);
    }

    public Menu getMenu() {
        return menu;
    }
}
