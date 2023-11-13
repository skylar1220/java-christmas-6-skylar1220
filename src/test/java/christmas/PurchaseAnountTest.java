package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.OrderGroup;
import christmas.domain.PurchaseAmount;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PurchaseAnountTest {
    public static Stream<Arguments> orderGroupData() {
        return Stream.of(
                Arguments.of(OrderGroup.from(List.of("바비큐립-2", "시저샐러드-1")), 116000),
                Arguments.of(OrderGroup.from(List.of("양송이수프-1")), 6000)
        );
    }

    @DisplayName("주문 목록을 통해 총 구매 금액을 구한다.")
    @ParameterizedTest
    @MethodSource("orderGroupData")
    void validateMenuCount(OrderGroup orderGroup, int amount) {
        // given & when
        PurchaseAmount purchaseAmount = PurchaseAmount.from(orderGroup);

        // then
        assertThat(purchaseAmount.getAmount()).isEqualTo(amount);
    }
}
