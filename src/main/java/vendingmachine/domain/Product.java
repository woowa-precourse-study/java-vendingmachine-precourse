package vendingmachine.domain;

import vendingmachine.exception.Validator;

public class Product {
    private final String name;
    private final int price;
    private int amount;

    public Product(String name, String price, String amount) {
        validateIsAlphabet(name);
        validatePriceFormat(price);
        Validator.validatePositiveNumber(amount);
        this.name = name;
        this.price = Integer.parseInt(price);
        this.amount = Integer.parseInt(amount);
    }

    private void validateIsAlphabet(String input) {
        if (!input.matches("[a-zA-Z가-힣]+")) {
            throw new IllegalArgumentException("문자가 아닙니다.");
        }
    }

    private void validatePriceFormat(String input) {
        Validator.validateIsNumber(input);
        int value = Integer.parseInt(input);
        if (value < 100 || value % 10 != 0) {
            throw new IllegalArgumentException("최소 상품 가격은 100원이며, 1원 단위는 불가능합니다.");
        }
    }

    private void validatePurchase() {
        if (amount <= 0) {
            throw new IllegalArgumentException("상품 재고가 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int purchase() {
        validatePurchase();
        amount--;
        return price;
    }

}
