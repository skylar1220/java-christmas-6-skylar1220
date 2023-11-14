package christmas.domain;

import christmas.domain.validator.VisitDateValidator;
import java.util.Objects;

public class VisitDate {
    private final int date;

    public VisitDate(int date) {
        this.date = date;
    }

    public static VisitDate from(int date) {
        VisitDateValidator.validate(date);
        return new VisitDate(date);
    }

    public int getDdayBonusDate() {
        return date - 1;
    }

    public int getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VisitDate visitDate = (VisitDate) o;
        return date == visitDate.date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
