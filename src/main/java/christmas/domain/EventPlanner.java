package christmas.domain;

public class EventPlanner {
    private final VisitingDate visitingDate;
    private final OrderDetails orderDetails;

    public EventPlanner(VisitingDate visitingDate, OrderDetails orderDetails) {
        this.visitingDate = visitingDate;
        this.orderDetails = orderDetails;
    }
}
