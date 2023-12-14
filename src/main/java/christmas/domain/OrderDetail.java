package christmas.domain;


public class OrderDetail {
    private final Menu menu;
    private final MenuCount menuCount;

    public OrderDetail(Menu menu, MenuCount menuCount) {
        this.menu = menu;
        this.menuCount = menuCount;
    }

    public static OrderDetail of(String menu, int menuCount) {
        return new OrderDetail(Menu.from(menu), new MenuCount(menuCount));
    }

    public boolean isDrink() {
        return menu.isCategory(Category.DRINKS);
    }

    public int calculatePriceSum() {
        return menu.calculatePrice(menuCount);
    }
}