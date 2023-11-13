package christmas.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DiscountSummary {
    private final EnumMap<Event, Integer> discountSummary;

    public DiscountSummary(EnumMap<Event, Integer> discountSummary) {
        this.discountSummary = discountSummary;
    }

    public static DiscountSummary from(VisitDate date, OrderGroup orderGroup) {
        return new DiscountSummary(getDiscountSummary(date, orderGroup));
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

    public EnumMap<Event, Integer> getDiscountSummary() {
        return discountSummary;
    }
}
