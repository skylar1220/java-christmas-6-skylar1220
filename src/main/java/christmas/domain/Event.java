package christmas.domain;

import java.util.List;

public enum Event {
    D_DAY("크리스마스 디데이", 1000, List.of(MenuCategory.values()), DateGroup.from("크리스마스 디데이")),
    WEEKDAY("평일", 2023, List.of(MenuCategory.DESSERT),  DateGroup.from("평일")),
    WEEKEND("주말", 2023, List.of(MenuCategory.MAIN),  DateGroup.from("주말")),
    SPECIAL("특별", 1000, List.of(MenuCategory.values()),  DateGroup.from("특별"));

    public static final int DAILY_BONUS_MONEY = 100;
    private final String name;
    private final int discount;
    private final List<MenuCategory> categories;
    private final List<VisitDate> dates;

    Event(String name, int discount, List<MenuCategory> categories, DateGroup dateGroup) {
        this.name = name;
        this.discount = discount;
        this.categories = categories;
        this.dates = dateGroup.getDataGroup();
    }

    public int getDiscountAmount(VisitDate date, OrderGroup orderGroup) {
        if (isEventDateMatching(D_DAY, date)) {
            return discount + getDdayBonus(date);
        }
        if (isEventDateMatching(SPECIAL, date)) {
            return discount;
        }
        if (isEventDateMatching(WEEKDAY, date) || isEventDateMatching(WEEKEND, date)) {
            int count = orderGroup.getCountByCategory(date, this);
            return discount * count;
        }
        return 0;
    }

    public boolean canApply(MenuCategory category, VisitDate date) {
        return categories.contains(category) && dates.contains(date);
    }

    private boolean isEventDateMatching(Event event, VisitDate date) {
        return this == event && dates.contains(date);
    }

    private int getDdayBonus(VisitDate date) {
        int bonusDate = date.getDdayBonusDate();
        return bonusDate * DAILY_BONUS_MONEY;
    }

    public String getName() {
        return name;
    }
}
