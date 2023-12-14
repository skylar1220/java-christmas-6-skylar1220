package christmas.domain;

import christmas.domain.eventDiscipline.ChristmasDdaEventDiscipline;
import christmas.domain.eventDiscipline.EventDiscipline;
import christmas.domain.eventDiscipline.SpecialEventDiscipline;
import christmas.domain.eventDiscipline.WeekDaysEventDiscipline;
import christmas.domain.eventDiscipline.WeekendsEventDiscipline;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {

    private final List<EventDiscipline> events;

    public EventHandler(VisitingDate visitingDate, OrderDetails orderDetails) {
        EventDiscipline christmasDdaEvent = new ChristmasDdaEventDiscipline(visitingDate, orderDetails);
        EventDiscipline weekDaysEventDiscipline = new WeekDaysEventDiscipline(visitingDate, orderDetails);
        EventDiscipline weekendsEventDiscipline = new WeekendsEventDiscipline(visitingDate, orderDetails);
        EventDiscipline specialEventDiscipline = new SpecialEventDiscipline(visitingDate, orderDetails);

        this.events = new ArrayList<>(List.of(christmasDdaEvent, weekDaysEventDiscipline, weekendsEventDiscipline, specialEventDiscipline));
    }

    public Map<EventName, Integer> calculateEventBenefit() {
        Map<EventName, Integer> eventBenefitSummary = new LinkedHashMap<>();
        events.forEach(event -> {
            EventName eventName = event.getEventName();
            int discount = event.calculateDiscount();
            eventBenefitSummary.put(eventName, discount);
        });
        return eventBenefitSummary;
    }
}
