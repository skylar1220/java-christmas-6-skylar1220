package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class DateGroup {

    public static final List<Integer> WEEKDAY_DAYS = List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25,
            26, 27, 28, 31);
    public static final List<Integer> WEEKEND_DAYS = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    public static final List<Integer> SPECAIL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private final List<VisitDate> dataGroup;

    public DateGroup(List<VisitDate> dateGroup) {
        this.dataGroup = dateGroup;
    }

    public static DateGroup from(String eventName) {
        return new DateGroup(getDateGroup(eventName));
    }

    private static List<VisitDate> getDateGroup(String eventName) {
        List<VisitDate> dateGroup = new ArrayList<>();
        if (eventName.equals("크리스마스 디데이")) {
            for (int date = 1; date <= 25; date++) {
                dateGroup.add(VisitDate.from(date));
            }
        }
        if (eventName.equals("평일")) {
            for (int date : WEEKDAY_DAYS) {
                dateGroup.add(VisitDate.from(date));
            }
        }
        if (eventName.equals("주말")) {
            for (int date : WEEKEND_DAYS) {
                dateGroup.add(VisitDate.from(date));
            }
        }
        if (eventName.equals("특별")) {
            for (int date : SPECAIL_DAYS) {
                dateGroup.add(VisitDate.from(date));
            }
        }
        return dateGroup;
    }

    public List<VisitDate> getDataGroup() {
        return dataGroup;
    }
}
