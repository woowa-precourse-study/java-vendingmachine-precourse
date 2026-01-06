package vendingmachine.exception;

public interface Validator {
    void validate(String input);


    static void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다.");
        }
    }

    static int validateIsNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 금액은 숫자여야 합니다.");
        }
    }

    static void validatePositiveNumber(String input) {
        int value = validateIsNumber(input);
        if (value < 0) {
            throw new IllegalArgumentException("[ERROR] 음수는 입력할 수 없습니다.");
        }
    }

    static void validateCoinUnit(String input) {
        int value = validateIsNumber(input);
        if (value % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 10원으로 나누어떨어져야합니다.");
        }
    }


}
