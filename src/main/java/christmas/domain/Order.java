package christmas.domain;

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

    public int sumAmountWith(int amount) {
        return amount + calculateAmount();
    }

    private int calculateAmount() {
        return count.getAmountByMenu(menu);
    }

    public int getEventAvailableCount(VisitDate date, Event event) {
        if (canApplyEvent(date, event)) {
            return count.getCount();
        }
        return 0;
    }

    public boolean canApplyEvent(VisitDate date, Event event) {
        return menu.canApplyEvent(date, event);
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getCountNumber() {
        return count.getCount();
    }
}
