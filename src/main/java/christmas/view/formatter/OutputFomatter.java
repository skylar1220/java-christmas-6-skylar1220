package christmas.view.formatter;

import christmas.common.Symbol;

public class OutputFomatter {
    public static final String WINNERS_SEPARATOR = Symbol.COMMA;


    public static String formatMoney(int money) {
        return String.format("%,d", money);
    }
}
