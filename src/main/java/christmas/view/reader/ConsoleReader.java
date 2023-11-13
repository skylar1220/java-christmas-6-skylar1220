package christmas.view.reader;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.reader.Reader;

public class ConsoleReader implements Reader {
    @Override
    public String readLine() {
        return Console.readLine();
    }
}
