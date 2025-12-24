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

    public Coins getCoins() {
        return coins;
    }

    public VendingMachine addProducts(Products products) {
        return new VendingMachine(products, coins);
    }

    public boolean canBuy(int price) {
        return !products.isSoldOut() && products.canBuy(price);
    }

    public static VendingMachine from(int price, CoinGenerator generator) {
        return new VendingMachine(generator.generate(price));
    }

    public int buyProduct(int fee, String productName) {
        return fee - products.getFee(productName);
    }

    public VendingMachine buyProduct(String productName) {
        Products newProducts = products.decreaseQuantity(productName);

        return new VendingMachine(newProducts, coins);
    }

    public void hasProduct(String productName) {
        products.findProductByName(productName);
    }
}
