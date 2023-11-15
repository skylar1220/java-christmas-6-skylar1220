package christmas.controller;

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
        showWelcome();
        VisitDate date = readWithRetry(this::getVisitDate);
        OrderGroup orderGroup = readWithRetry(this::getOrderGroup);
        showEventSummary(date, orderGroup);
    }

    private void showWelcome() {
        outputView.printWelcome();
    }

    private VisitDate getVisitDate() {
        int date = readWithRetry(inputView::inputDate);
        return VisitDate.from(date);
    }

    private OrderGroup getOrderGroup() {
        List<String> orderGroup = readWithRetry(inputView::inputOrder);
        return OrderGroup.from(orderGroup);
    }

    private void showEventSummary(VisitDate date, OrderGroup orderGroup) {
        DiscountSummary discountSummary = DiscountSummary.from(date, orderGroup);

        outputView.printEventPreMessage(date);
        outputView.printEvent(orderGroup, discountSummary);
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
