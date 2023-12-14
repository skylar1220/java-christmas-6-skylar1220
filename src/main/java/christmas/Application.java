package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.view.InputView;
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
        OutputView outputView = new OutputView(printer);

        ChristmasPromotionController christmasPromotionController = new ChristmasPromotionController(inputView, outputView);
        christmasPromotionController.run();
    }
}
