package christmas;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {
    @DisplayName("날짜는 1~31일 사이여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void inputDay(int input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> VisitDate.from(input))
                .withMessage(InputValidator.DATE_IS_INVALID);
    }
}
