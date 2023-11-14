package christmas.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class DiscountSummary {
    private final EnumMap<Event, Integer> summary;
    private final FreeGift freeGift;

    public DiscountSummary(EnumMap<Event, Integer> summary, FreeGift freeGift) {
        this.summary = summary;
        this.freeGift = freeGift;
    }

    public static DiscountSummary from(VisitDate date, OrderGroup orderGroup) {
        return new DiscountSummary(getDiscountSummary(date, orderGroup), orderGroup.getFreeGift());
    }

    private static EnumMap<Event, Integer> getDiscountSummary(VisitDate date, OrderGroup orderGroup) {
        Map<Event, Integer> discountSummary = new EnumMap<>(Event.class);
        if (orderGroup.canApplyEvent()) {
            Arrays.stream(Event.values()).forEach(event -> {
                int discountAmount = event.getDiscountAmount(date, orderGroup);
                if (discountAmount != 0) {
                    discountSummary.put(event, discountAmount);
                }
            });
        }
        return new EnumMap<>(discountSummary);
    }

    public int getDiscountBeforeGift() {
        return summary.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getDiscountWithGift() {
        return freeGift.sumWith(getDiscountBeforeGift());
    }

    public EnumMap<Event, Integer> getSummary() {
        return summary;
    }

    public String getBadgeName() {
        Badge badge =  Badge.from(getDiscountWithGift());
        return badge.getName();
    }
    public Badge getBadge() {
        return  Badge.from(getDiscountWithGift());
    }

    public boolean hasDiscount() {
        return summary.values().stream()
                .anyMatch(discount -> discount != 0);
    }

    public boolean hasFreeGift() {
        return freeGift != FreeGift.NOTHING;
    }

    public int getFreeGiftPrice() {
        return freeGift.getPrice();
    }
}
