package christmas.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<Event, Integer> discountSummary = Arrays.stream(Event.values())
                .collect(Collectors.toMap(event -> event, event -> 0));

        if (orderGroup.canApplyEvent()) {
            Arrays.stream(Event.values()).forEach(event ->
                    discountSummary.merge(event, event.getDiscountAmount(date, orderGroup), Integer::sum)
            );
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

    public Badge getBadge() {
        return Badge.from(getDiscountWithGift());
    }
}
