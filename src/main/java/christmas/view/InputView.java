package christmas.view;

import static christmas.view.validator.InputValidator.ORDER_SEPARATOR;

import christmas.util.Converter;
import christmas.view.validator.InputValidator;
import christmas.view.printer.Printer;
import christmas.view.reader.Reader;
import java.util.List;

public class InputView {
    private final Reader reader;
    private final Printer printer;
    private final InputValidator validator;

    private InputView(Reader reader, Printer printer, InputValidator validator) {
        this.reader = reader;
        this.printer = printer;
        this.validator = validator;
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer, InputValidator.getInstance());
    }

    public int inputDate() {
        printer.printLine("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String date = reader.readLine();
        validator.validateDate(date);
        return Converter.convertToInt(date);
    }

    public List<String> inputOrder() {
        printer.printLine("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String orderGroup = reader.readLine();
        validator.validateOrderGroup(orderGroup);
        validator.validateOrder(Converter.splitToList(ORDER_SEPARATOR, orderGroup));
        validator.validateMenuCount(Converter.splitToList(ORDER_SEPARATOR, orderGroup));
        return Converter.splitToList(ORDER_SEPARATOR, orderGroup);
    }
}
