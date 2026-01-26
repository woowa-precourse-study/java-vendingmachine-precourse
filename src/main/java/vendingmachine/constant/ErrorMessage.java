package vendingmachine.constant;

public enum ErrorMessage {

    AMOUNT_NUMBER_FORMAT_ERROR("금액은 숫자여야 합니다."),
    AMOUNT_UNIT_ERROR("금액은 10원 단위여야 합니다."),
    INVALID_AMOUNT("유효하지 않은 금액입니다."),

    INPUT_FORMAT_ERROR("입력 형식이 올바르지 않습니다."),
    ITEM_NAME_UNIQUE_ERROR("상품명 중복입니다."),
    PRICE_MIN_ERROR("가격은 100원 이상이어야 합니다."),
    PRICE_UNIT_ERROR("가격은 10원 단위여야 합니다."),

    NO_MIN_PRICE_ERROR("최소 금액이 존재하지 않습니다."),

    NO_EXIST_ITEM_ERROR("존재하지 않는 상품입니다."),
    EMPTY_STOCK_ITEM_ERROR("재고가 없는 상품입니다."),
    ;

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(Object... args) {
        return ERROR_MESSAGE_PREFIX + String.format(errorMessage, args);
    }
}
