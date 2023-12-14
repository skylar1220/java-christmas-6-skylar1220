package christmas.mock;


import christmas.view.printer.Printer;

public class FakePrinter implements Printer {
    @Override
    public void printLine(String message) {

    }

    @Override
    public void printLine(int number) {

    }

    @Override
    public void printLine(String format, Object... args) {

    }

    @Override
    public void printEmptyLine() {

    }
}
