package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuTest {
    @DisplayName("메뉴판에 없는 메뉴를 입력하면 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"스파게티", "피자"})
    void getInvalidMenu(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Menu.from(input))
                .withMessage(ErrorMessage.ORDER_IS_INVALID);
    }

    @DisplayName("메뉴판에 있는 메뉴면 이름에 맞는 메뉴를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"양송이수프, MUSHROOM_SOUP"})
    void getMenu(String name, Menu menu) {
        assertThat(Menu.from(name)).isEqualTo(menu);
    }
}
