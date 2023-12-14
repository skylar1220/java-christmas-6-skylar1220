package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitingDate {
    private final LocalDate visitindgDate;

    public VisitingDate(LocalDate visitindgDate) {
        this.visitindgDate = visitindgDate;
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

    public int calculateBonusDate() {
        return visitindgDate.getDayOfMonth() - LocalDate.of(2023, 12, 1).getDayOfMonth();
    }

    public boolean isInDateRange(LocalDate date) {
        return visitindgDate.getDayOfMonth() <= date.getDayOfMonth();
    }

    public boolean isWeekDays() {
        List<DayOfWeek> weekdays = List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
        return weekdays.contains(visitindgDate.getDayOfWeek());
    }

    public boolean isWeekend() {
        List<DayOfWeek> weekends = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        return weekends.contains(visitindgDate.getDayOfWeek());
    }

    public boolean isContainedFrom(List<LocalDate> dates) {
        return dates.contains(visitindgDate);
    }
}
