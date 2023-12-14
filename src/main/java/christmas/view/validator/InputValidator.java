package christmas.view.validator;

import christmas.common.Symbol;
import christmas.util.converter.Converter;
import christmas.util.validator.GeneralValidator;
import christmas.util.validator.StringValidator;
import java.util.List;

public class InputValidator {
    private static InputValidator inputValidator;
    public static final String ORDER_SEPARATOR = Symbol.COMMA;
    public static final String MENU_AND_COUNT_SEPARATOR = Symbol.HYPHEN;

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

    public void validateVisitingDate(String visitingDate, String message) {
        StringValidator.validateBlank(visitingDate, message);
        StringValidator.validateNumeric(visitingDate, message);
        StringValidator.validateIntegerRange(visitingDate, message);
    }

    public void validateOrderDetails(String rawOrderDetails, String message) {
        validateOrderDetailsFormat(ORDER_SEPARATOR, rawOrderDetails, message);
        List<String> orderDetails = Converter.splitToList(ORDER_SEPARATOR, rawOrderDetails);
        orderDetails.forEach(orderDetail -> validateOrderDetailFormat(MENU_AND_COUNT_SEPARATOR, orderDetail, message));
        orderDetails.forEach(orderDetail -> validateEachMenuFormat(MENU_AND_COUNT_SEPARATOR, orderDetail, message));
        orderDetails.forEach(
                orderDetail -> validateEachMenuCountFormat(MENU_AND_COUNT_SEPARATOR, orderDetail, message));
    }

    private void validateEachMenuCountFormat(String separator, String orderDetail, String message) {
        String menuCount = Converter.getSplittedValueOf(separator, 1, orderDetail);
        StringValidator.validateBlank(menuCount, message);
        StringValidator.validateNumeric(menuCount, message);
        StringValidator.validateIntegerRange(menuCount, message);
    }

    private void validateEachMenuFormat(String separator, String orderDetail, String message) {
        String menu = Converter.getSplittedValueOf(separator, 0, orderDetail);
        StringValidator.validateBlank(menu, message);
    }

    private void validateOrderDetailFormat(String separator, String orderDetail, String message) {
        StringValidator.validateBlank(orderDetail, message);
        GeneralValidator.validateDuplicateSubstring(separator, orderDetail, message);
        GeneralValidator.validateStartSubstring(separator, orderDetail, message);
        GeneralValidator.validateEndSubstring(separator, orderDetail, message);
        GeneralValidator.validateSplittedCount(separator, orderDetail, 2, message);
    }

    private void validateOrderDetailsFormat(String separator, String orderDetails, String message) {
        StringValidator.validateBlank(orderDetails, message);
        GeneralValidator.validateDuplicateSubstring(separator, orderDetails, message);
        GeneralValidator.validateStartSubstring(separator, orderDetails, message);
        GeneralValidator.validateEndSubstring(separator, orderDetails, message);
        validateMenuDuplicates(orderDetails);
    }

    private void validateMenuDuplicates(String orderDetails) {
        List<String> menuNames = Converter.splitToList(ORDER_SEPARATOR, orderDetails).stream()
                .map(splitedValue -> Converter.getSplittedValueOf(MENU_AND_COUNT_SEPARATOR, 0, splitedValue))
                .toList();
        GeneralValidator.validateDuplicateValues(menuNames, "유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

//    public static void validateNumber(String template, String target) {
//        StringValidator.validateBlank(template, target);
//        StringValidator.validateNumeric(template, target);
//        StringValidator.validateIntegerRange(template, target);
//    }
//
//    public void validatList(String template, String target) {
//        StringValidator.validateBlank(template, target);
//        GeneralValidator.validateDuplicateSubstring(Symbol.COMMA, template, target);
//        GeneralValidator.validateStartSubstring(Symbol.COMMA, template, target);
//        GeneralValidator.validateEndSubstring(Symbol.COMMA, template, target);
//        GeneralValidator.validateSplittedCount(Symbol.COMMA, template, 2, target);
//    }
//
//    public void validateListEachValue(List<String> values, String target) {
//        values.forEach(value -> StringValidator.validateBlank(value, target));
//        values.forEach(value -> StringValidator.validateNumeric(value, target));
//        values.forEach(value -> StringValidator.validateIntegerRange(value, target));
//    }
}
