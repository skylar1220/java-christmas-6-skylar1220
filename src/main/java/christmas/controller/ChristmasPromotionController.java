package christmas.controller;

import christmas.domain.EventPlanner;
import christmas.domain.FreeGift;
import christmas.domain.OrderDetails;
import christmas.domain.PurchaseAmount;
import christmas.domain.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Function;
import java.util.function.Supplier;

public class ChristmasPromotionController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasPromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcome();
        VisitingDate visitingDate = inputView.inputVisitingDate();
        OrderDetails orderDetails = inputView.inputOrderDetails();

        PurchaseAmount purchaseAmount = orderDetails.calculatePurchaseAmount();
        FreeGift freeGift = FreeGift.from(purchaseAmount);
        EventPlanner eventPlanner = new EventPlanner(visitingDate, orderDetails);
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
