package christmas.domain;

import christmas.view.validator.InputValidator;
import java.util.Arrays;

public enum Menu {
    양송이수프(6000, MenuCategory.APPETIZER),
    타파스(5500, MenuCategory.APPETIZER),
    시저샐러드(8000, MenuCategory.APPETIZER),
    티본스테이크(55000, MenuCategory.MAIN),
    바비큐립(54000, MenuCategory.MAIN),
    해산물파스타(35000, MenuCategory.MAIN),
    크리스마스파스타(25000, MenuCategory.MAIN),
    초코케이크(15000, MenuCategory.DESSERT),
    아이스크림(5000, MenuCategory.DESSERT),
    제로콜라(3000, MenuCategory.DRINK),
    레드와인(60000, MenuCategory.DRINK),
    샴페인(25000, MenuCategory.DRINK),
    없음(0, MenuCategory.DRINK);
    private final int price;
    private final MenuCategory category;

    Menu(int price, MenuCategory category) {
        this.price = price;
        this.category = category;
    }

    public static Menu from(String menu) {
        return getMenu(menu);
    }

    private static Menu getMenu(String rawMenu) {
        return Arrays.stream(values())
                .filter(menu -> menu.name().equals(rawMenu))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(InputValidator.ORDER_IS_INVALID));
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
}
