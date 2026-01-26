package vendingmachine.domain;

import vendingmachine.constant.ErrorMessage;

public class InsertedMoney {

    private static final int UNIT = 10;

    private int amount;

    private InsertedMoney(int amount) {
        this.amount = amount;
    }

    public static InsertedMoney fromAmount(int money) {
        validate(money);

        return new InsertedMoney(money);
    }

    private static void validate(int amount) {
        if (amount % UNIT != 0 ) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_UNIT_ERROR.getErrorMessage());
        }
    }

    public void updateAmount(int purchasePrice) {
        amount -= purchasePrice;
    }

    public int getAmount() {
        return amount;
    }
}
