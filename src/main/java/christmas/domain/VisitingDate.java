package christmas.domain;

import java.time.LocalDate;

public class VisitingDate {
    private final LocalDate visitindDate;

    public VisitingDate(LocalDate visitindDate) {
        this.visitindDate = visitindDate;
    }

    public static VisitingDate from(int visitingDate) {
        validateRange(visitingDate);
        return new VisitingDate(LocalDate.of(2023, 12, visitingDate));
    }

    private static void validateRange(int visitingDate) {
        if (!isValidRange(visitingDate)) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요");
        }
    }

    private static boolean isValidRange(int visitingDate) {
        return visitingDate >= 1 && visitingDate <= 31;
    }
}
