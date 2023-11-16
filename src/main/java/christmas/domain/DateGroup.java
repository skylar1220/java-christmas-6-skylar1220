package christmas.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class DateGroup {
    private static final List<Integer> WEEKDAY_DAYS = List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25,
            26, 27, 28, 31);
    private static final List<Integer> WEEKEND_DAYS = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final List<Integer> SPECAIL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final Map<String, List<Integer>> EVENT_DAY_MAP = Map.of(
            "크리스마스 디데이", getDays(1, 25),
            "평일", WEEKDAY_DAYS,
            "주말", WEEKEND_DAYS,
            "특별", SPECAIL_DAYS
    );

    private final List<VisitDate> dataGroup;

    private DateGroup(List<VisitDate> dateGroup) {
        this.dataGroup = dateGroup;
    }

    public static DateGroup from(String eventName) {
        return new DateGroup(getDateGroup(eventName));
    }

    public List<VisitDate> getDataGroup() {
        return Collections.unmodifiableList(dataGroup);
    }

    private static List<Integer> getDays(int startInclusive, int endInclusive) {
        return IntStream.rangeClosed(startInclusive, endInclusive)
                .boxed()
                .toList();
    }

    private static List<VisitDate> getDateGroup(String eventName) {
        return EVENT_DAY_MAP.getOrDefault(eventName, Collections.emptyList())
                .stream()
                .map(VisitDate::from)
                .toList();
    }
}
