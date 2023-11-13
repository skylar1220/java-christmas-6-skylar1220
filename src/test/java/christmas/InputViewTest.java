package christmas;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {
    private Printer printer = new FakePrinter();

    @DisplayName("날짜의 입력 형식은 공백이 아니고, 숫자이며, integer 범위내여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "a1", "2147483648", "-2147483649"})
    void inputDay(String input) {
        // given
        Reader reader = new FakeReader(input);
        InputView inputView = InputView.of(reader, printer);

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(inputView::inputDate)
                .withMessage(InputValidator.DATE_IS_INVALID);
    }
}
