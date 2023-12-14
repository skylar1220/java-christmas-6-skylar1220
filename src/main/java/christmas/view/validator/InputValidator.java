package christmas.view.validator;

import christmas.common.Symbol;
import java.util.List;
import mission.common.Symbol;

public class InputValidator {
    private static InputValidator inputValidator;
    public static final String TEMPLATE_SEPARATOR = Symbol.COMMA;

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
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
