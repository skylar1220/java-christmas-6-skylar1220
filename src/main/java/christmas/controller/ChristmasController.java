package christmas.controller;

import christmas.domain.DiscountSummary;
import christmas.view.InputView;
import christmas.domain.OrderGroup;
import christmas.view.OutputView;
import christmas.domain.VisitDate;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int rawDate = readWithRetry(inputView::inputDate);
        VisitDate date = VisitDate.from(rawDate);

        List<String> rawOrderGroup = readWithRetry(inputView::inputOrder);
        OrderGroup orderGroup = OrderGroup.from(rawOrderGroup);

        outputView.printPreMessageOfEvent(date);
        outputView.printOrderGroup(orderGroup);
        outputView.printPurchaseAmount(orderGroup);
        outputView.printFreeGift(orderGroup);

        DiscountSummary discountSummary = DiscountSummary.from(date, orderGroup);
        outputView.printDiscountSummary(discountSummary);
        outputView.printTotalDiscount(discountSummary);
    }

    private <T> T readWithRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(supplier);
        }
    }

    private <T, R> R readWithRetry(Function<T, R> function, T input) {
        try {
            return function.apply(input);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(function, input);
        }
    }
}
