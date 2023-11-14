package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.stream.Collectors;

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
        return Arrays.stream(Event.values())
                .filter(event -> orderGroup.canApplyEvent()
                        && event.getDiscountAmount(date, orderGroup) > 0)
                .collect(Collectors.toMap(
                        event -> event,
                        event -> event.getDiscountAmount(date, orderGroup),
                        (a, b) -> a,
                        () -> new EnumMap<>(Event.class)));
    }

    public EnumMap<Event, Integer> getSummary() {
        return new EnumMap<>(Collections.unmodifiableMap(summary));
    }
}
