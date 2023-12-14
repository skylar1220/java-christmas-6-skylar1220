package christmas.domain.eventDiscipline;

import christmas.domain.Category;
import christmas.domain.EventName;
import christmas.domain.OrderDetails;
import christmas.domain.VisitingDate;

public class WeekDaysEventDiscipline implements EventDiscipline {
    private final VisitingDate visitingDate;
    private final OrderDetails orderDetails;
    private final EventName eventName;
    private final Category category;

    public WeekDaysEventDiscipline(VisitingDate visitingDate, OrderDetails orderDetails) {
        this.visitingDate = visitingDate;
        this.orderDetails = orderDetails;
        this.category = Category.DESSERT;
        this.eventName = new EventName("평일");
    }

    @Override
    public EventName getEventName() {
        return eventName;
    }

    @Override
    public int calculateDiscount() {
        if (visitingDate.isWeekDays()) {
            return orderDetails.calculateDiscountOf(category, 2023);
        }
        return 0;
    }
}
