package christmas.domain;

public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료"),
    NOTHING("없음");

    private final String name;

    MenuCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
