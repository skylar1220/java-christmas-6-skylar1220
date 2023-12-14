package christmas.domain.eventDiscipline;

import christmas.domain.EventName;
import christmas.domain.OrderDetails;
import christmas.domain.VisitingDate;
import java.time.LocalDate;

public class ChristmasDdaEventDiscipline implements EventDiscipline {
    private final VisitingDate visitingDate;
    private final OrderDetails orderDetails;
    private final EventName eventName;

    public ChristmasDdaEventDiscipline(VisitingDate visitingDate, OrderDetails orderDetails) {
        this.visitingDate = visitingDate;
        this.orderDetails = orderDetails;
        this.eventName = new EventName("크리스마스 디데이");
    }

    @Override
    public EventName getEventName() {
        return eventName;
    }

    @Override
    public int calculateDiscount() {
        if (visitingDate.isInDateRange(LocalDate.of(2023, 12, 25))) {
            int bonusDate = visitingDate.calculateBonusDate();
            return 1000 + (bonusDate * 100);
        }
        return 0;
    }
}
