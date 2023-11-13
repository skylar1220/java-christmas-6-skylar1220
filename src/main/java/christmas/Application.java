package christmas;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Reader reader = new ConsoleReader();
        Printer printer = new ConsolePrinter();
        InputView inputView = InputView.of(reader, printer);

        OutputFomatter outputFomatter = new OutputFomatter();
        OutputView outputView = new OutputView(printer, outputFomatter);

        ChristmasController christmasController = new ChristmasController(inputView, outputView);
        christmasController.run();
    }
}
