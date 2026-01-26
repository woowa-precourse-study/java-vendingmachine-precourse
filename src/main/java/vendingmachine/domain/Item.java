package vendingmachine.domain;

import vendingmachine.constant.ErrorMessage;

public class Item {

    private static final int UNIT = 10;
    private static final int MIN_PRICE = 100;

    private final String name;
    private final int price;

    public Item(String name, int price) {
        validate(price);

        this.name = name;
        this.price = price;
    }

    private void validate(int price) {
        validateMin(price);
        validateUnit(price);
    }

    private static void validateUnit(int price) {
        if (price % UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.PRICE_UNIT_ERROR.getErrorMessage());
        }
    }

    private static void validateMin(int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.PRICE_MIN_ERROR.getErrorMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
