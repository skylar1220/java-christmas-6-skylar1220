package christmas;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.view.InputView;
import christmas.view.printer.Printer;
import christmas.view.reader.Reader;
import christmas.view.validator.InputValidator;
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

    @DisplayName("메뉴와 갯수 조합은 ,로 구분된 올바른 형식으로 입력되어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", ",타파스-1,코카콜라-1", ",타파스-1,코카콜라-1,", "타파스-1,,코카콜라-1"})
    void validateRawMenuWithCount(String input) {
        // given
        Reader reader = new FakeReader(input);
        InputView inputView = InputView.of(reader, printer);

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(inputView::inputOrder)
                .withMessage(InputValidator.ORDER_IS_INVALID);
    }

    @DisplayName("개별 메뉴-갯수 조합의 입력 형식은 -로 구분된 올바른 형식으로 입력되어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-,시저샐러드-1", "-1,시저샐러드-1", "타파스--1,시저샐러드-1"})
    void validateMenuWithCount(String input) {
        // given
        Reader reader = new FakeReader(input);
        InputView inputView = InputView.of(reader, printer);

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(inputView::inputOrder)
                .withMessage(InputValidator.ORDER_IS_INVALID);
    }

    @DisplayName("개별 메뉴-갯수에서 갯수의 입력 형식은 공백이 아니고, 숫자이며, integer 범위내여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-육,시저샐러드-1", "타파스-2147483648,시저샐러드-1"})
    void validateMenuCount(String input) {
        // given
        Reader reader = new FakeReader(input);
        InputView inputView = InputView.of(reader, printer);

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(inputView::inputOrder)
                .withMessage(InputValidator.ORDER_IS_INVALID);
    }
}
