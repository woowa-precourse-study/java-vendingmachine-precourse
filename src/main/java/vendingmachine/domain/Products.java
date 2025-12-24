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
            List<String> product = Parser.splitStringToListWithSymbols(input.substring(1, input.length() - 1), ",");
            this.products.add(new Product(product.get(0), product.get(1), product.get(2)));
        }
    }

}
