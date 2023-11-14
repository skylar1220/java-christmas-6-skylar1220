package christmas.domain;

import java.util.Arrays;

public enum FreeGift {
    CHAMPAGNE(Menu.CHAMPAGNE, 120000),
    NOTHING(Menu.NOTHING, 0)
    ;

    private final String name;
    private final int price;
    private final int criterionAmount;

    FreeGift(Menu menu, int criterionAmount) {
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.criterionAmount = criterionAmount;
    }

    public static FreeGift from(int amount) {
        return Arrays.stream(values())
                .filter(gift -> gift.criterionAmount <= amount)
                .findFirst()
                .orElse(NOTHING);
    }

    public int sumWith(int other) {
        return price + other;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
