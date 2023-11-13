package christmas;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderGroupTest {
    public static Stream<Arguments> invalidCountGroup() {
        return Stream.of(
                Arguments.of(List.of("양송이수프-19", "타파스-2")),
                Arguments.of(List.of("양송이수프-21"))
        );
    }

    @DisplayName("메뉴 갯수는 20개 이하여야 한다.")
    @ParameterizedTest
    @MethodSource("invalidCountGroup")
    void getInvalidMenu(List<String> input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderGroup.from(input))
                .withMessage(InputValidator.ORDER_IS_INVALID);
    }
    public static Stream<Arguments> duplicatesData() {
        return Stream.of(
                Arguments.of(List.of("양송이수프-1", "타파스-2", "양송이수프-2")),
                Arguments.of(List.of("타파스-1", "타파스-1"))
        );
    }

    @DisplayName("메뉴는 중복되지 않아야 한다.")
    @ParameterizedTest
    @MethodSource("duplicatesData")
    void validateDuplicates(List<String> input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderGroup.from(input))
                .withMessage(InputValidator.ORDER_IS_INVALID);
    }

    public static Stream<Arguments> onlyDrinkData() {
        return Stream.of(
                Arguments.of(List.of("제로콜라-1", "레드와인-2")),
                Arguments.of(List.of("제로콜라-1"))
        );
    }

    @DisplayName("주문에 음료만 있으면 안된다.")
    @ParameterizedTest
    @MethodSource("onlyDrinkData")
    void validateCategoryOnly(List<String> input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderGroup.from(input))
                .withMessage(OrderGroupValidator.ORDER_GROUP_CONTAIN_ONLY_DRINK);
    }
}
