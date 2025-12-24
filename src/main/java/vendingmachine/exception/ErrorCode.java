package vendingmachine.exception;

public enum ErrorCode {
    NOT_A_NUMBER("숫자가 아닙니다."),
    NOT_POSITIVE_NUMBER("숫자가 음수입니다."),
    NOT_VALID_COIN("10으로 나누어 떨어지지 않습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
