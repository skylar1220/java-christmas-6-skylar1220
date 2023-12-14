package christmas.view;

import christmas.domain.Badge;
import christmas.domain.EventBenefitSummary;
import christmas.domain.EventName;
import christmas.domain.FinalPurchaseAmout;
import christmas.domain.FreeGift;
import christmas.domain.OrderDetail;
import christmas.domain.OrderDetails;
import christmas.domain.PurchaseAmount;
import christmas.domain.TotalBenefitAmout;
import christmas.view.formatter.OutputFomatter;
import christmas.view.printer.Printer;
import java.util.Map.Entry;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";
    private final Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

//    public void printTemplate(Template rawTemplate) {
//        int template = OutputFomatter.toTemplate(rawTemplate);
//        printer.printLine("%d개", template);
//    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
        printer.printEmptyLine();
    }

    public void printWelcome() {
        printer.printLine("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOrderDetails(OrderDetails orderDetails) {
        printer.printLine("<주문 메뉴>");
        orderDetails.getOrderDetails().forEach(orderDetail -> printOrderDetail(orderDetail));
    }

    private void printOrderDetail(OrderDetail orderDetail) {
        printer.printLine("%s %d개", orderDetail.getMenu().getName(), orderDetail.getMenuCount().getMenuCount());
    }

    public void printPurchaseAmount(PurchaseAmount purchaseAmount) {
        printer.printLine("<할인 전 총주문 금액>");
        printer.printLine("%s원", OutputFomatter.formatMoney(purchaseAmount.getPurchaseAmount()));
    }

    public void printFreeGift(FreeGift freeGift) {
        printer.printLine("<증정 메뉴>");
        if (freeGift.equals(FreeGift.NOTHING)) {
            printer.printLine("없음");
            return;
        }
        printer.printLine("%s 1개", freeGift.getMenu().getName());
    }

    public void printBenefirSummary(EventBenefitSummary eventBenefitSummary, FreeGift freeGift) {
        printer.printLine("<혜택 내역>");
        eventBenefitSummary.getEventBenefitSummary().entrySet().forEach(this::printEvent);
        if (!freeGift.equals(FreeGift.NOTHING)) {
            printer.printLine("증정 이벤트: -25000원");
        }
        if (eventBenefitSummary.isNothing()){
            printer.printLine("없음");
        }
    }

    private void printEvent(Entry<EventName, Integer> eventWithDiscount) {
        if (eventWithDiscount.getValue() == 0) {
            return;
        }
        printer.printLine("%s 할인: -%s원", eventWithDiscount.getKey().getEventName(), OutputFomatter.formatMoney(eventWithDiscount.getValue()));
    }

    public void printTotalBenefitAmount(TotalBenefitAmout totalBenefitAmout) {
        printer.printLine("<총혜택 금액>");
        printer.printLine("-%s원", OutputFomatter.formatMoney(totalBenefitAmout.getTotalBenefitAmount()));
    }

    public void printFinalPurchaseAmount(FinalPurchaseAmout finalPurchaseAmout) {
        printer.printLine("<할인 후 예상 결제 금액>");
        printer.printLine("%s원", OutputFomatter.formatMoney(finalPurchaseAmout.getFinalPurchaseAmount()));
    }

    public void printBadge(Badge badge) {
        printer.printLine("<12월 이벤트 배지>");
        if (!badge.equals(Badge.NOTHING)){
            printer.printLine(badge.getName());
            return;
        }
        printer.printLine("없음");
    }
}
