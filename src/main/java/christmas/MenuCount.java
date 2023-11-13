package christmas;

public class MenuCount {
    private final int count;

    public MenuCount(int count) {
        this.count = count;
    }

    public static MenuCount from(int count) {
        MenuCountValidator.validate(count);
        return new MenuCount(count);
    }

    public int sumWith(int otherCount) {
        return count + otherCount;
    }
}
