package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;

public class DiscountSummary {
    private final EnumMap<Event, Integer> summary;
    private final FreeGift freeGift;

    private DiscountSummary(EnumMap<Event, Integer> summary, FreeGift freeGift) {
        this.summary = summary;
        this.freeGift = freeGift;
    }

    public static DiscountSummary from(VisitDate date, OrderGroup orderGroup) {
        EnumMap<Event, Integer> discountSummary = getDiscountSummary(date, orderGroup);
        FreeGift freeGift = orderGroup.getFreeGift();

        return new DiscountSummary(discountSummary, freeGift);
    }

    public String getBadgeName() {
        Badge badge = Badge.from(getDiscountWithGift());
        return badge.getName();
    }

    public Badge getBadge() {
        return Badge.from(getDiscountWithGift());
    }

    public int getDiscountWithGift() {
        return freeGift.sumWith(getDiscountBeforeGift());
    }

    public int getDiscountBeforeGift() {
        return summary.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getFreeGiftPrice() {
        return freeGift.getPrice();
    }

    public boolean hasDiscount() {
        return summary.values().stream()
                .anyMatch(discount -> discount != 0);
    }

    public boolean hasFreeGift() {
        return freeGift != FreeGift.NOTHING;
    }

    private static EnumMap<Event, Integer> getDiscountSummary(VisitDate date, OrderGroup orderGroup) {
        EnumMap<Event, Integer> discountSummary = new EnumMap<>(Event.class);

        if (orderGroup.canApplyEvent()) {
            Arrays.stream(Event.values())
                    .filter(event -> event.getDiscountAmount(date, orderGroup) > 0)
                    .forEach(event -> discountSummary.put(event, event.getDiscountAmount(date, orderGroup)));
        }
        return discountSummary;
    }

    public EnumMap<Event, Integer> getSummary() {
        return new EnumMap<>(Collections.unmodifiableMap(summary));
    }
}
