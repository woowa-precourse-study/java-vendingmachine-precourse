package vendingmachine.factory;

import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.Product;
import vendingmachine.domain.Products;
import vendingmachine.util.InputParser;

public class ProductFactory {

    public static Products createProducts(List<String> products) {

        List<Product> items = products.stream()
                .map(product -> {
                    String detail = product.substring(1, product.length() - 1);
                    String[] split = detail.split(",");

                    String name = split[0];
                    int price = InputParser.parseNumber(split[1]);
                    int quantity = InputParser.parseNumber(split[2]);

                    return Product.of(name, price, quantity);
                })
                .collect(Collectors.toList());

        return Products.from(items);
    }
}
