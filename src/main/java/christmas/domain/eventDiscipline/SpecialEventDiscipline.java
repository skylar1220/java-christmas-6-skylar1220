package christmas.domain.eventDiscipline;

import christmas.domain.EventName;
import christmas.domain.OrderDetails;
import christmas.domain.VisitingDate;
import java.time.LocalDate;
import java.util.List;

public class SpecialEventDiscipline implements EventDiscipline {
    private final VisitingDate visitingDate;
    private final OrderDetails orderDetails;
    private final EventName eventName;

    public SpecialEventDiscipline(VisitingDate visitingDate, OrderDetails orderDetails) {
        this.visitingDate = visitingDate;
        this.orderDetails = orderDetails;
        this.eventName = new EventName("특별");
    }

    @Override
    public EventName getEventName() {
        return eventName;
    }

    @Override
    public int calculateDiscount() {
        List<LocalDate> dates = List.of(
                LocalDate.of(2023, 12, 3),
                LocalDate.of(2023, 12, 10),
                LocalDate.of(2023, 12, 17),
                LocalDate.of(2023, 12, 24),
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2023, 12, 31)
        );

        if (visitingDate.isContainedFrom(dates)) {
            return 1000;
        }
        return 0;
    }
}
