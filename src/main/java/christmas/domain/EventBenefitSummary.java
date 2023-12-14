package christmas.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventBenefitSummary {
    private final Map<EventName, Integer> eventBenefitSummary;

    public EventBenefitSummary(Map<EventName, Integer> eventBenefitSummary) {
        this.eventBenefitSummary = eventBenefitSummary;
    }

    public static EventBenefitSummary empty() {
        Map<EventName, Integer> eventBenefitSummary = new LinkedHashMap<>();
        return new EventBenefitSummary(eventBenefitSummary);
    }

    public static EventBenefitSummary from(Map<EventName, Integer> eventBenefitSummary) {
        return new EventBenefitSummary(eventBenefitSummary);
    }

    public int calculateTotalBenefitAmout() {
        return eventBenefitSummary.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<EventName, Integer> getEventBenefitSummary() {
        return eventBenefitSummary;
    }

    public boolean isNothing() {
        return eventBenefitSummary.values().stream()
                .allMatch(discount -> discount == 0);
    }
}
