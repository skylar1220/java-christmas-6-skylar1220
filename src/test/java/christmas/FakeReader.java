package christmas;

public class FakeReader implements Reader {
    private final String input;

    public FakeReader(String input) {
        this.input = input;
    }

    @Override
    public String readLine() {
        return input;
    }
}
