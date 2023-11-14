package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.common.ErrorMessage;
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
                .withMessage("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
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
                .withMessage(ErrorMessage.ORDER_IS_INVALID);
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
                .withMessage("음료만 주문할 수는 없습니다. 다시 입력해주세요");
    }


    public static Stream<Arguments> amountData() {
        return Stream.of(
                Arguments.of(List.of("시저샐러드-1"), 8000),
                Arguments.of(List.of("티본스테이크-1", "바비큐립-1", "시저샐러드-1"), 117000),
                Arguments.of(List.of("티본스테이크-2", "바비큐립-2", "초코케이크-2", "아이스크림-1", "시저샐러드-1"), 261000),
                Arguments.of(List.of("타파스-1", "제로콜라-1"), 8500)
        );
    }

    @DisplayName("주문 목록을 통해 총 구매 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("amountData")
    void calculateAmount(List<String> rawOrderGroup, int amount) {
        OrderGroup orderGroup = OrderGroup.from(rawOrderGroup);
        assertThat(orderGroup.calculateAmount()).isEqualTo(amount);
    }


    public static Stream<Arguments> freeGiftData() {
        return Stream.of(
                Arguments.of(List.of("시저샐러드-1"), FreeGift.NOTHING),
                Arguments.of(List.of("티본스테이크-1", "바비큐립-1", "시저샐러드-1"), FreeGift.NOTHING ),
                Arguments.of(List.of("티본스테이크-2", "바비큐립-2", "초코케이크-2", "아이스크림-1", "시저샐러드-1"), FreeGift.CHAMPAGNE)
        );
    }

    @DisplayName("(증정 혜택 적용 전) 총 할인 금액을 통해 증정 혜택을 부여한다.")
    @ParameterizedTest
    @MethodSource("freeGiftData")
    void getFreeGift(List<String> rawOrderGroup, FreeGift gift) {
        // given
        OrderGroup orderGroup = OrderGroup.from(rawOrderGroup);

        // when & then
        assertThat(orderGroup.getFreeGift()).isEqualTo(gift);
    }
}
