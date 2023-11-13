package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING("없음", 0);

    private final String name;
    private final int criterionAmount;

    Badge(String name, int criterionAmount) {
        this.name = name;
        this.criterionAmount = criterionAmount;
    }

    public static Badge from(int amount) {
        return Arrays.stream(values())
                .filter(badge -> badge.criterionAmount <= amount)
                .findFirst()
                .orElse(NOTHING);
    }
}
