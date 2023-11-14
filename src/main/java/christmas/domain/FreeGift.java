package christmas.domain;

import java.util.Arrays;

public enum FreeGift {
    샴페인(Menu.샴페인, 120000),
    없음(Menu.없음, 0)
    ;

    private final String name;
    private final int price;
    private final int criterionAmount;

    FreeGift(Menu menu, int criterionAmount) {
        this.name = menu.name();
        this.price = menu.getPrice();
        this.criterionAmount = criterionAmount;
    }

    public static FreeGift from(int amount) {
        return Arrays.stream(values())
                .filter(gift -> gift.criterionAmount <= amount)
                .findFirst()
                .orElse(없음);
    }

    public int sumWith(int other) {
        return price + other;
    }
}
