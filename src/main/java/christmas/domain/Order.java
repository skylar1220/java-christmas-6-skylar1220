package christmas.domain;

import java.util.List;

public class Order {
    private final Menu menu;
    private final MenuCount count;

    public Order(Menu menu, MenuCount count) {
        this.menu = menu;
        this.count = count;
    }

    public int addCount(int otherCount) {
        return count.addCount(otherCount);
    }

    public boolean hasDuplicateMenu(List<Order> orderGroup) {
        return orderGroup.stream()
                .filter(order -> order.isSameMenu(menu))
                .count() >= 2;
    }

    public boolean isMenuCategory(MenuCategory category) {
        return menu.isCategory(category);
    }

    public int sumAmountWith(int amount) {
        return amount + calculateAmount();
    }

    public int getCountByCategory(List<MenuCategory> categories) {
        if (menu.hasCategory(categories)){
            return count.getCount();
        }
        return 0;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getCountNumber() {
        return count.getCount();
    }

    private boolean isSameMenu(Menu otherMenu) {
        return menu == otherMenu;
    }

    private int calculateAmount() {
        return count.calculateAmountForMenu(menu);
    }
}
