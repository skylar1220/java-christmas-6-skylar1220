package christmas;

public class InputView {
    private final Reader reader;
    private final Printer printer;
    private final InputValidator validator;

    public InputView(Reader reader, Printer printer, InputValidator validator) {
        this.reader = reader;
        this.printer = printer;
        this.validator = validator;
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer, InputValidator.getInstance());
    }

    public int inputDate() {
        printer.printLine("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String rawDate = reader.readLine();
        validator.validateDate(rawDate);
        return Converter.convertToInt(rawDate);
    }
}
