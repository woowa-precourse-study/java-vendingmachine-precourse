package vendingmachine.domain;

import vendingmachine.domain.vo.Money;

public class VendingMachine {
    private final Money money;
    private final Coins coins;

    public VendingMachine(int price, Coins coins) {
        money = Money.won(price);
        this.coins = coins;
    }

    public static VendingMachine from(int price, CoinGenerator generator) {
        return new VendingMachine(price, generator.generate(price));
    }
}
