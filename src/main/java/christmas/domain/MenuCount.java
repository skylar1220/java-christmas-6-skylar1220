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
}
