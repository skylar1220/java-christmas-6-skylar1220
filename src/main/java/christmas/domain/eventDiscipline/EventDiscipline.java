package christmas.domain.eventDiscipline;

import christmas.domain.EventName;

public interface EventDiscipline {
    EventName getEventName();

    int calculateDiscount();
}
