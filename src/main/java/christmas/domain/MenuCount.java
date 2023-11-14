package christmas.domain;

import christmas.domain.validator.MenuCountValidator;

public class MenuCount {
    private final int count;

    private MenuCount(int count) {
        this.count = count;
    }

    public static MenuCount from(int count) {
        MenuCountValidator.validate(count);
        return new MenuCount(count);
    }

    public int calculateAmountForMenu(Menu menu) {
        return menu.getAmountByCount(count);
    }

    public int addCount(int otherCount) {
        return count + otherCount;
    }

    public int getCount() {
        return count;
    }
}
