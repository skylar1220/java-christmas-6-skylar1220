package christmas.view;

import christmas.domain.DiscountSummary;
import christmas.domain.Event;
import christmas.domain.Order;
import christmas.domain.OrderGroup;
import christmas.domain.VisitDate;
import christmas.view.printer.Printer;
import java.util.Map.Entry;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";
    private final Printer printer;
    private final OutputFomatter formatter;

    public OutputView(Printer printer, OutputFomatter formatter) {
        this.printer = printer;
        this.formatter = formatter;
    }

    public void printEventPreMessage(VisitDate rawDate) {
        int date = formatter.toDate(rawDate);

        printer.printLine("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date);
        printer.printEmptyLine();
    }

    public void printEvent(OrderGroup orderGroup, DiscountSummary discountSummary) {
        printOrderGroup(orderGroup);
        printOrderAmount(orderGroup);
        printFreeGift(orderGroup);
        printDiscountSummary(discountSummary);
        printTotalDiscount(discountSummary);
        printFianlAmount(orderGroup, discountSummary);
        printBadge(discountSummary);
    }

    public void printOrderGroup(OrderGroup orderGroup) {
        printer.printLine("<주문 메뉴>");
        orderGroup.getOrderGroup().forEach(this::printOrder);
        printer.printEmptyLine();
    }

    private void printOrder(Order order) {
        String menu = formatter.toMenu(order);
        int count = formatter.toMenuCount(order);

        printer.printLine("%s %d개", menu, count);
    }

    public void printOrderAmount(OrderGroup orderGroup) {
        String amount = formatter.toOrderAmount(orderGroup);

        printer.printLine("<할인 전 총주문 금액>");
        printer.printLine("%s원", amount);
        printer.printEmptyLine();
    }

    public void printFreeGift(OrderGroup orderGroup) {
        String gift = formatter.toFreeGift(orderGroup);

        printer.printLine("<증정 메뉴>");
        printer.printLine(gift);
        printer.printEmptyLine();
    }

    public void printDiscountSummary(DiscountSummary discountSummary) {
        printer.printLine("<혜택 내역>");
        if (discountSummary.hasDiscount()) {
            discountSummary.getSummary().entrySet().forEach(this::printDiscount);
        }
        if (!discountSummary.hasDiscount()) {
            printer.printLine("없음");
        }
        if (discountSummary.hasFreeGift()) {
            String giftPrice = formatter.toFreeGiftPrice(discountSummary);
            printer.printLine("증정 이벤트: %s원", giftPrice);
        }
        printer.printEmptyLine();
    }

    private void printDiscount(Entry<Event, Integer> eachDiscountSummary) {
        String event = formatter.toEventName(eachDiscountSummary);
        String discount = formatter.toDiscount(eachDiscountSummary);

        printer.printLine("%s 할인: %s원", event, discount);
    }

    public void printTotalDiscount(DiscountSummary discountSummary) {
        String totalDiscount = formatter.toTotalDiscount(discountSummary);

        printer.printLine("<총혜택 금액>");
        printer.printLine("%s원", totalDiscount);
        printer.printEmptyLine();
    }

    public void printFianlAmount(OrderGroup orderGroup, DiscountSummary discountSummary) {
        String fianlAmount = formatter.toFinalAmount(orderGroup, discountSummary);

        printer.printLine("<할인 후 예상 결제 금액>");
        printer.printLine("%s원", fianlAmount);
        printer.printEmptyLine();
    }

    public void printBadge(DiscountSummary discountSummary) {
        String badge = formatter.toBadge(discountSummary);

        printer.printLine("<12월 이벤트 배지>");
        printer.printLine(badge);
    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
        printer.printEmptyLine();
    }
}
