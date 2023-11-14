package christmas.view;

import christmas.domain.Order;
import christmas.domain.OrderGroup;
import christmas.domain.VisitDate;
import christmas.view.printer.Printer;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";
    private final Printer printer;
    private final OutputFomatter formatter;

    public OutputView(Printer printer, OutputFomatter formatter) {
        this.printer = printer;
        this.formatter = formatter;
    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
    }

    public void printOrderGroup(OrderGroup orderGroup) {
        orderGroup.getOrderGroup().forEach(this::printOrder);
    }

    public void printPreMessageOfEvent(VisitDate rawDate) {
        int date = formatter.toDate(rawDate);
        printer.printLine("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date);
        printer.printEmptyLine();
    }

    private void printOrder(Order order) {
        String menu = formatter.toMenu(order);
        int count = formatter.toMenuCount(order);
        printer.printLine("<주문 메뉴>");
        printer.printLine("%s %d개", menu, count);
    }
}
