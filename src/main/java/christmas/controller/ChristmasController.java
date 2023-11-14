package christmas.controller;

import christmas.domain.Amount;
import christmas.domain.DiscountSummary;
import christmas.domain.OrderGroup;
import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        VisitDate date = readWithRetry(this::getVisitDate);
        OrderGroup orderGroup = readWithRetry(this::getOrderGroup);
        showEventSummary(date, orderGroup);
    }

    private VisitDate getVisitDate() {
        int rawDate = readWithRetry(inputView::inputDate);
        return VisitDate.from(rawDate);
    }

    private OrderGroup getOrderGroup() {
        List<String> rawOrderGroup = readWithRetry(inputView::inputOrder);
        return OrderGroup.from(rawOrderGroup);
    }

    private void showEventSummary(VisitDate date, OrderGroup orderGroup) {
        DiscountSummary discountSummary = DiscountSummary.from(date, orderGroup);
        Amount finalAmount = Amount.of(orderGroup, discountSummary);

        outputView.printEventPreMessage(date);
        outputView.printEvent(orderGroup, discountSummary, finalAmount);
    }

    private <T> T readWithRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(supplier);
        }
    }
}
