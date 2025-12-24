package vendingmachine.domain;

public class VendingMachine {
    private final Products products;
    private final Coins coins;

    public VendingMachine(Coins coins) {
        this(null, coins);
    }

    public VendingMachine(Products products, Coins coins) {
        this.products = products;
        this.coins = coins;
    }

    public static VendingMachine from(int price, CoinGenerator generator) {
        return new VendingMachine(generator.generate(price));
    }

    public Coins getCoins() {
        return coins;
    }

    public VendingMachine addProducts(Products products) {
        return new VendingMachine(products, coins);
    }
}
