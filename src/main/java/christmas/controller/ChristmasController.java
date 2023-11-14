package christmas.controller;

import christmas.domain.Amount;
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

        VisitDate date = readWithRetry(this::getVisitDate);
        OrderGroup orderGroup = readWithRetry(this::getOrderGroup);

        outputView.printPreMessageOfEvent(date);
        outputView.printOrderGroup(orderGroup);
        outputView.printPurchaseAmount(orderGroup);
        outputView.printFreeGift(orderGroup);

        DiscountSummary discountSummary = DiscountSummary.from(date, orderGroup);
        outputView.printDiscountSummary(discountSummary);
        outputView.printTotalDiscount(discountSummary);

        Amount amount = Amount.of(orderGroup, discountSummary);
        outputView.printFianlAmount(amount);
        outputView.printBadge(discountSummary);
    }

    private OrderGroup getOrderGroup() {
        List<String> rawOrderGroup = readWithRetry(inputView::inputOrder);
        return OrderGroup.from(rawOrderGroup);
    }

    private VisitDate getVisitDate() {
        int rawDate = readWithRetry(inputView::inputDate);
        return VisitDate.from(rawDate);
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
