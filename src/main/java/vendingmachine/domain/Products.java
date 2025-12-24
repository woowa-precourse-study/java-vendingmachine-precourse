package vendingmachine.domain;

import vendingmachine.exception.Validator;
import vendingmachine.utils.Constants;
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

    public void validateProduct(String inputProduct) {
        validateContainProduct(inputProduct);
        validateProductAmountEnough(inputProduct);
    }

    public boolean validatePurchaseAvailable(int price) {
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

    public int getPurchaseProductAmount(String purchaseProduct) {
        for (Product product : products) {
            if (product.getName().equals(purchaseProduct)) {
                return product.purchase();
            }
        }
        return Constants.INVALID_PURCHASE;
    }

    private void validateContainProduct(String purchaseProduct) {
        for (Product product : products) {
            if (product.getName().equals(purchaseProduct)) {
                return;
            }
        }
        throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
    }

    private void validateProductAmountEnough(String inputProduct) {
        for (Product product : products) {
            if (product.getName().equals(inputProduct) && product.getAmount() == 0) {
                throw new IllegalArgumentException("상품 재고가 없습니다.");
            }
        }
    }


}
