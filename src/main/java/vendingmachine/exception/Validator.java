package vendingmachine.exception;

import java.util.List;

public interface Validator {
    void validate(String input);

    static void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.");
        }
    }

    static void validateIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

    static void validatePositiveNumber(String input) {
        validateIsNumber(input);
        int value = Integer.parseInt(input);
        if (value < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
    }

    static void validatePurchaseInputFormat(String input) {
        List<String> inputs = List.of(input.split(";"));
        for (String ip : inputs) {
            validateNotBlank(ip);
            validateStartAndEndWithBrackets(ip);
        }
    }


    static void validateStartAndEndWithBrackets(String input) {
        if (!input.startsWith("[") || !input.endsWith("]")) {
            throw new IllegalArgumentException("입력형식이 올바르지 않습니다.");
        }
    }
    
}


