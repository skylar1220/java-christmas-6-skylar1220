package christmas.domain;

public class EventPlanner {
    private final VisitingDate visitingDate;
    private final OrderDetails orderDetails;
    private final EventHandler eventHandler;

    public EventPlanner(VisitingDate visitingDate, OrderDetails orderDetails) {
        this.visitingDate = visitingDate;
        this.orderDetails = orderDetails;
        this.eventHandler = new EventHandler(visitingDate, orderDetails);
    }

    public EventBenefitSummary calculateEventBenefit() {
        if (!orderDetails.isEventAvailable()) {
            return EventBenefitSummary.empty();
        }
        return EventBenefitSummary.from(eventHandler.calculateEventBenefit());
    }
}
