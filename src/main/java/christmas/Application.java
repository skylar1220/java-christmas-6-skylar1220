package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputView;
import christmas.view.formatter.OutputFomatter;
import christmas.view.OutputView;
import christmas.view.printer.ConsolePrinter;
import christmas.view.printer.Printer;
import christmas.view.reader.ConsoleReader;
import christmas.view.reader.Reader;

public class Application {
    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Printer printer = new ConsolePrinter();
        InputView inputView = InputView.of(reader, printer);

        OutputFomatter outputFomatter = new OutputFomatter();
        OutputView outputView = new OutputView(printer, outputFomatter);

        ChristmasController christmasController = new ChristmasController(inputView, outputView);
        christmasController.run();
    }
}
