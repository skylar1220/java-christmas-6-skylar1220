package christmas.domain;

import christmas.common.ErrorMessage;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuCategory.APPETIZER),
    TBONE_STEAK("티본스테이크", 55000, MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", 54000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuCategory.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, MenuCategory.DESSERT),
    ICECREAM("아이스크림", 5000, MenuCategory.DESSERT),
    COKE_ZERO("제로콜라", 3000, MenuCategory.DRINK),
    RED_WINE("레드와인", 60000, MenuCategory.DRINK),
    CHAMPAGNE("샴페인", 25000, MenuCategory.DRINK),
    NOTHING("없음", 0, MenuCategory.NOTHING);

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
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.ORDER_IS_INVALID));
    }

    public boolean isCategory(MenuCategory otherCategory) {
        return category == otherCategory;
    }

    public int getAmountByCount(int count) {
        return price * count;
    }

    public boolean canApplyEvent(VisitDate date, Event event) {
        return event.canApply(category, date);
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
