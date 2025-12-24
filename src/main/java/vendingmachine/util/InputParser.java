package vendingmachine.util;

import static vendingmachine.exception.ErrorCode.NOT_A_NUMBER;

public final class InputParser {
    private static final String NUMBER_REGEX = "\\d+";

    public static int parseNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException(NOT_A_NUMBER.getMessage());
        }

        return Integer.parseInt(input);
    }
}
