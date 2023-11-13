package christmas.domain;

import christmas.view.validator.InputValidator;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    Caesar_Salad("시저샐러드", 8000, MenuCategory.APPETIZER),
    TBONE_STEAK("티본스테이크", 55000, MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", 54000, MenuCategory.MAIN),
    Seafood_PASTA("해산물파스타", 35000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuCategory.MAIN),
    CHOCOLATE_CAKE("초코케이크", 5000, MenuCategory.DESSERT),
    ICECREAM("아이스크림", 5000, MenuCategory.DESSERT),
    COKE_ZERO("제로콜라", 3000, MenuCategory.DRINK),
    RED_WINE("레드와인", 60000, MenuCategory.DRINK),
    CHAMPAGNE("샴페인", 250000, MenuCategory.DRINK);

    private final String name;
    private final int price;
    private final MenuCategory category;

    Menu(String name, int price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Menu from(String menu) {
        return getMenu(menu);
    }

    private static Menu getMenu(String rawMenu) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(rawMenu))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(InputValidator.ORDER_IS_INVALID));
    }

    public boolean isCategory(MenuCategory otherCategory) {
        return category == otherCategory;
    }
}
