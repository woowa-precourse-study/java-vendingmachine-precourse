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
            ip = ip.substring(1, ip.length() - 1);
            List<String> ips = List.of(ip.split(","));
            validateIsAlphabet(ips.get(0));
            validatePriceFormat(ips.get(1));
            validatePositiveNumber(ips.get(2));
        }
    }

    static void validateIsAlphabet(String input) {
        if (!input.matches("[a-zA-Z가-힣]+")) {
            throw new IllegalArgumentException("문자가 아닙니다.");
        }
    }

    static void validateStartAndEndWithBrackets(String input) {
        if (!input.startsWith("[") || !input.endsWith("]")) {
            throw new IllegalArgumentException("입력형식이 올바르지 않습니다.");
        }
    }

    static void validatePriceFormat(String input) {
        validateIsNumber(input);
        int value = Integer.parseInt(input);
        if (value < 100 || value % 10 != 0) {
            throw new IllegalArgumentException("최소 상품 가격은 100원이며, 1원 단위는 불가능합니다.");
        }
    }
}


