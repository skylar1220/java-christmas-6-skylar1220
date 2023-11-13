package christmas;

import java.util.List;

public class Order {
    private final Menu menu;
    private final MenuCount count;

    public Order(Menu menu, MenuCount count) {
        this.menu = menu;
        this.count = count;
    }

    public int sumCountWith(int otherCount) {
        return count.sumWith(otherCount);
    }

    public boolean hasSameMenu(List<Order> orderGroup) {
        return orderGroup.stream()
                .filter(order -> order.isSameMenu(menu))
                .count() >= 2;
    }

    private boolean isSameMenu(Menu otherMenu) {
        return menu == otherMenu;
    }

    public boolean isMenuCategory(MenuCategory category) {
        return menu.isCategory(category);
    }
}
