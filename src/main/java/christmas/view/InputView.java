package christmas.view;


import christmas.common.Symbol;
import christmas.domain.OrderDetails;
import christmas.domain.VisitingDate;
import christmas.util.converter.Converter;
import christmas.view.printer.Printer;
import christmas.view.reader.Reader;
import christmas.view.validator.InputValidator;

public class InputView {

    public static final String ORDER_SEPARATOR = Symbol.COMMA;
    public static final String MENU_AND_COUNT_SEPARATOR = Symbol.HYPHEN;
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

    public VisitingDate inputVisitingDate() {
        printer.printLine("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String visitingDate = reader.readLine();
        validator.validateVisitingDate(visitingDate, "유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        return VisitingDate.from(Converter.convertToInt(visitingDate));
    }

    public OrderDetails inputOrderDetails() {
        printer.printLine("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String orderDetails = reader.readLine();
        validator.validateOrderDetails(orderDetails, "유효하지 않은 주문입니다. 다시 입력해 주세요.");
        return OrderDetails.from(Converter.splitToList(ORDER_SEPARATOR, orderDetails));
    }

//    public Template inputTemplate() {
//        printer.printLine("");
//        String template = reader.readLine();
//        validator.validate(template, "템플릿");
//        return new Template();
//    }
}
