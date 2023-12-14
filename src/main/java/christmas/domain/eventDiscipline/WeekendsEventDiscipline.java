package christmas.domain.eventDiscipline;

import christmas.domain.Category;
import christmas.domain.EventName;
import christmas.domain.OrderDetails;
import christmas.domain.VisitingDate;

public class WeekendsEventDiscipline implements EventDiscipline {
    private final VisitingDate visitingDate;
    private final OrderDetails orderDetails;
    private final EventName eventName;
    private final Category category;

    public WeekendsEventDiscipline(VisitingDate visitingDate, OrderDetails orderDetails) {
        this.visitingDate = visitingDate;
        this.orderDetails = orderDetails;
        this.eventName = new EventName("주");
        this.category = Category.MAIN;
    }

    @Override
    public EventName getEventName() {
        return eventName;
    }

    @Override
    public int calculateDiscount() {
        if (visitingDate.isWeekend()) {
            return orderDetails.calculateDiscountOf(category, 2023);
        }
        return 0;
    }
}
