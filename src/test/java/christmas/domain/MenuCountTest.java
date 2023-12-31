package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuCountTest {
    @DisplayName("개별 메뉴 개수는 1보다 커야한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void validateMenuCount(int input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> MenuCount.from(input))
                .withMessage(ErrorMessage.ORDER_IS_INVALID);
    }
}
