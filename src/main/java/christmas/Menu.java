package christmas;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, MenuCategory.MAIN),
    TAPAS("타파스", 5500, MenuCategory.MAIN),

    COKE_ZERO("제로콜라", 5500, MenuCategory.DRINK),
    RED_WINE("레드와인", 5500, MenuCategory.DRINK);

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
