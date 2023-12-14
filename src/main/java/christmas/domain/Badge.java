package christmas.domain;

import java.util.Arrays;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NOTHING("", 0);

    private final String name;
    private final int minBenefitAmount;

    Badge(String name, int minBenefitAmount) {
        this.name = name;
        this.minBenefitAmount = minBenefitAmount;
    }

    public static Badge from(FinalPurchaseAmout input) {
        return Arrays.stream(values())
                .filter(badge ->  input.isOverOrEqual(badge.minBenefitAmount))
                .findAny()
                .orElse(NOTHING);
    }

    public String getName() {
        return name;
    }
}
