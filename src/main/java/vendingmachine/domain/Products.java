package vendingmachine.domain;

import java.util.List;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public static Products from(List<Product> products) {
        return new Products(products);
    }
}
