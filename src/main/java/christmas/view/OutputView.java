package christmas.view;

import christmas.view.printer.Printer;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";
    private final Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

//    public void printTemplate(Template rawTemplate) {
//        int template = OutputFomatter.toTemplate(rawTemplate);
//        printer.printLine("%d개", template);
//    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
        printer.printEmptyLine();
    }

    public void printWelcome() {
        printer.printLine("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
}
