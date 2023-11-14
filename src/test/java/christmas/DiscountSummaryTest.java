package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Badge;
import christmas.domain.DiscountSummary;
import christmas.domain.Event;
import christmas.domain.OrderGroup;
import christmas.domain.VisitDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountSummaryTest {
    public static Stream<Arguments> discountSummary() {
        List<String> main2_dessert3_order = List.of("티본스테이크-2", "초코케이크-2", "아이스크림-1");
        return Stream.of(
                Arguments.of(1, main2_dessert3_order, Event.D_DAY, 1000),
                Arguments.of(1, main2_dessert3_order, Event.WEEKEND, 4046),

                Arguments.of(25, main2_dessert3_order, Event.D_DAY, 3400),
                Arguments.of(25, main2_dessert3_order, Event.WEEKDAY, 6069),
                Arguments.of(25, main2_dessert3_order, Event.SPECIAL, 1000),

                Arguments.of(26, main2_dessert3_order, Event.WEEKDAY, 6069)
        );
    }

    @DisplayName("주문 목록을 통해 할인 항목별 할인 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("discountSummary")
    void getDiscountSummary(int date, List<String> orderGroup, Event event, int discount) {
        DiscountSummary discountSummary = DiscountSummary.from(VisitDate.from(date),
                OrderGroup.from(orderGroup));
        assertThat(discountSummary.getSummary())
                .containsEntry(event, discount);
    }

    public static Stream<Arguments> discountBeforeGiftData() {
        return Stream.of(
                Arguments.of(1, List.of("시저샐러드-1"), 0),
                Arguments.of(1, List.of("티본스테이크-2", "바비큐립-2", "초코케이크-2", "아이스크림-1", "시저샐러드-1"), 9092),
                Arguments.of(3, List.of("티본스테이크-2", "바비큐립-2", "초코케이크-2", "아이스크림-1", "시저샐러드-1"), 8269),
                Arguments.of(25, List.of("티본스테이크-2", "바비큐립-2", "초코케이크-2", "아이스크림-1", "시저샐러드-1"), 10469),
                Arguments.of(26, List.of("티본스테이크-2", "바비큐립-2", "초코케이크-2", "아이스크림-1", "시저샐러드-1"), 6069)
        );
    }

    @DisplayName("주문 목록을 통해 (증정 혜택 적용 전) 총 할인 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("discountBeforeGiftData")
    void getDiscountBeforeGift(int date, List<String> orderGroup, int discountBeforeGift) {
        DiscountSummary discountSummary = DiscountSummary.from(VisitDate.from(date),
                OrderGroup.from(orderGroup));
        assertThat(discountSummary.getDiscountBeforeGift()).isEqualTo(discountBeforeGift);
    }

    public static Stream<Arguments> discountWithGiftData() {
        return Stream.of(
                Arguments.of(3, List.of("시저샐러드-1"), 0),
                Arguments.of(3, List.of("티본스테이크-1", "바비큐립-1", "시저샐러드-1"), 2200),
                Arguments.of(26, List.of("타파스-1", "제로콜라-1"), 0),
                Arguments.of(3, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"), 31246)
        );
    }

    @DisplayName("총 할인 금액에 증정 헤택을 더한 최종 할인 금액을 계산한다")
    @ParameterizedTest
    @MethodSource("discountWithGiftData")
    void getDiscountWithGift(int date, List<String> orderGroup, int discountWithGift) {
        DiscountSummary discountSummary = DiscountSummary.from(VisitDate.from(date),
                OrderGroup.from(orderGroup));
        assertThat(discountSummary.getDiscountWithGift()).isEqualTo(discountWithGift);
    }

    public static Stream<Arguments> badgeData() {
        return Stream.of(
                Arguments.of(3, List.of("시저샐러드-1"), Badge.NOTHING),
                Arguments.of(3, List.of("티본스테이크-1", "초코케이크-2"), Badge.STAR),
                Arguments.of(3, List.of("타파스-1", "초코케이크-2", "아이스크림-3"), Badge.TREE),
                Arguments.of(3, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"), Badge.SANTA)
        );
    }

    @DisplayName("총 혜택 금액을 통해 이벤트 배지를 부여한다")
    @ParameterizedTest
    @MethodSource("badgeData")
    void getBadge(int date, List<String> orderGroup, Badge badge) {
        DiscountSummary discountSummary = DiscountSummary.from(VisitDate.from(date),
                OrderGroup.from(orderGroup));
        assertThat(discountSummary.getBadge()).isEqualTo(badge);
    }
}
