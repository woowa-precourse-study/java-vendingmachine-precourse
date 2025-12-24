package vendingmachine.util;

import static vendingmachine.exception.ErrorCode.NOT_A_NUMBER;

import java.util.List;
import vendingmachine.exception.ErrorCode;

public final class InputParser {
    private static final String NUMBER_REGEX = "\\d+";
    private static final String PRODUCT_DELIMITER = ",";

    public static int parseNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException(NOT_A_NUMBER.getMessage());
        }

        return Integer.parseInt(input);
    }

    public static List<String> parseProduct(String input) {
        List<String> split = List.of(input.split(";"));

        split.forEach(InputParser::validateProduct);

        return split;
    }

    private static void validateProduct(String input) {
        if (!input.startsWith("[") || !input.endsWith("]")) {
            throw new IllegalArgumentException(ErrorCode.NOT_VALID_PRODUCT.getMessage());
        }

        List<String> split = List.of(input.split(PRODUCT_DELIMITER));

        if (split.size() != 3) {
            throw new IllegalArgumentException(ErrorCode.NOT_VALID_PRODUCT.getMessage());
        }
    }
}
