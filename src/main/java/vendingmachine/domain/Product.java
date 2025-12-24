package vendingmachine.domain;

public class Product {
    private final String name;
    private final int price;
    private final int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Product of(String name, int price, int quantity) {
        return new Product(name, price, quantity);
    }
}
