package vendingmachine.domain.vo;

import static vendingmachine.exception.ErrorCode.NOT_POSITIVE_NUMBER;

import vendingmachine.exception.ErrorCode;

public class Money {
    private final int price;

    private Money(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public static Money won(int price) {
        validatePrice(price);
        return new Money(price);
    }

    private static void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(NOT_POSITIVE_NUMBER.getMessage());
        }

        if (price % 10 != 0) {
            throw new IllegalArgumentException(ErrorCode.NOT_VALID_COIN.getMessage());
        }
    }
}
