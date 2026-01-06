package vendingmachine.domain;

import vendingmachine.exception.Validator;
import vendingmachine.utils.Parser;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<Product> products = new ArrayList<>();

    public Products(List<String> inputs) {
        for (String input : inputs) {
            Validator.validateStartAndEndWithBrackets(input);
            List<String> product = Parser.splitBy(input.substring(1, input.length() - 1), ",");
            this.products.add(new Product(product.get(0), Integer.parseInt(product.get(1)), Integer.parseInt(product.get(2))));
        }
    }


    private Product findByName(String name){
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        throw new IllegalArgumentException("해당 상품이 없습니다.");
    }

    public boolean isPurchaseAvailable(int price) {
        int minPrice = Integer.MAX_VALUE;
        int totalAmount = 0;
        for (Product product : products) {
            minPrice = Integer.min(product.getPrice(), minPrice);
            totalAmount += product.getAmount();
        }
        if (price < minPrice || totalAmount == 0) {
            return false;
        }
        return true;
    }

    public int purchase(String productName) {
        Product product = findByName(productName);
        return product.purchase();
    }

}
