package christmas;

public class VisitDate {
    private final int date;

    public VisitDate(int date) {
        this.date = date;
    }

    public static VisitDate from(int date) {
        VisitDateValidator.validate(date);
        return new VisitDate(date);
    }
}
