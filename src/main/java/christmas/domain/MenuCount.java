package christmas.domain;

public class MenuCount {
    private final int menuCount;

    public MenuCount(int menuCount) {
        validateRange(menuCount);
        this.menuCount = menuCount;
    }

    private void validateRange(int menuCount) {
        if (!isInRange(menuCount)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isInRange(int menuCount) {
        return menuCount >= 1 && menuCount <= 20;
    }

    public int calculatePriceByCount(int price) {
        return price * menuCount;
    }

    public int calculateDiscountByCount(int discount) {
        return menuCount * discount;
    }

    public int getMenuCount() {
        return menuCount;
    }
}
