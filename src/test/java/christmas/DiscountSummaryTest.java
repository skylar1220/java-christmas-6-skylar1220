package christmas;

import static org.assertj.core.api.Assertions.assertThat;

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
        return Stream.of(
                Arguments.of(1, List.of("시저샐러드-1"), 0, 0, 0, 0),
                Arguments.of(1, List.of("티본스테이크-2", "바비큐립-2","초코케이크-2", "아이스크림-1", "시저샐러드-1"), 1000, 0, 8092, 0),
                Arguments.of(3, List.of("티본스테이크-2", "바비큐립-2","초코케이크-2", "아이스크림-1", "시저샐러드-1"), 1200, 6069, 0, 1000),
                Arguments.of(25, List.of("티본스테이크-2", "바비큐립-2","초코케이크-2", "아이스크림-1", "시저샐러드-1"), 3400, 6069, 0, 1000),
                Arguments.of(26, List.of("티본스테이크-2", "바비큐립-2","초코케이크-2", "아이스크림-1", "시저샐러드-1"), 0, 6069, 0, 0)
        );
    }

    @DisplayName("주문 목록을 통해 할인 항목별 할인 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("discountSummary")
    void getDiscountSummary(int date, List<String> orderGroup, int dDayDC, int weekdayDC, int weekendDC, int specialDC) {
        DiscountSummary discountSummary = DiscountSummary.from(VisitDate.from(date),
                OrderGroup.from(orderGroup));
        assertThat(discountSummary.getDiscountSummary())
                .containsEntry(Event.D_DAY, dDayDC)
                .containsEntry(Event.WEEKDAY, weekdayDC)
                .containsEntry(Event.WEEKEND, weekendDC)
                .containsEntry(Event.SPECIAL, specialDC);
    }
    public static Stream<Arguments> discountBeforeGiftData() {
        return Stream.of(
                Arguments.of(1, List.of("시저샐러드-1"), 0),
                Arguments.of(1, List.of("티본스테이크-2", "바비큐립-2","초코케이크-2", "아이스크림-1", "시저샐러드-1"), 9092),
                Arguments.of(3, List.of("티본스테이크-2", "바비큐립-2","초코케이크-2", "아이스크림-1", "시저샐러드-1"), 8269),
                Arguments.of(25, List.of("티본스테이크-2", "바비큐립-2","초코케이크-2", "아이스크림-1", "시저샐러드-1"), 10469),
                Arguments.of(26, List.of("티본스테이크-2", "바비큐립-2","초코케이크-2", "아이스크림-1", "시저샐러드-1"), 6069)
        );
    }
    @DisplayName("주문 목록을 통해 (증정 혜택 적용 전) 총 할인 금액을 계산하는 기능을 계산한다.")
    @ParameterizedTest
    @MethodSource("discountBeforeGiftData")
    void getDiscountBeforeGift(int date, List<String> orderGroup, int discountBeforeGift) {
        DiscountSummary discountSummary = DiscountSummary.from(VisitDate.from(date),
                OrderGroup.from(orderGroup));
        assertThat(discountSummary.getDiscountBeforeGift()).isEqualTo(discountBeforeGift);
    }
}
