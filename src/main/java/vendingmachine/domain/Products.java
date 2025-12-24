package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public boolean isSoldOut() {
        return products.stream()
                .allMatch(Product::isSoldOut);
    }

    public boolean canBuy(int price) {
        return products.stream()
                .anyMatch(product -> product.canBuy(price));
    }

    public static Products from(List<Product> products) {
        return new Products(products);
    }

    public int getFee(String productName) {
        Product product = findProductByName(productName);

        return product.getPrice();
    }

    public Product findProductByName(String productName) {
        return products.stream()
                .filter(product -> product.hasName(productName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 상품입니다."));
    }

    public Products decreaseQuantity(String productName) {
        List<Product> newProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.hasName(productName)) {
                Product decreased = product.decreaseQuantity();
                newProducts.add(decreased);

                continue;
            }

            newProducts.add(product);
        }

        return new Products(newProducts);
    }
}
