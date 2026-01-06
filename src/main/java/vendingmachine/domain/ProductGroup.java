package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductGroup {
    private final List<Product> products=new ArrayList<>();
    private int minimumPrice=0;

    public void add(Product product) {
        products.add(product);
    }

    public void decrease(Product product){
        product.decreaseInventory();
    }

    public boolean isInventoryEnough(int amount){
        int inventory = products.stream()
                .mapToInt(Product::getInventory)
                .sum();
        return !(inventory<amount);
    }

    public boolean isEnoughMoney(int price){
        int minPrice= products.stream()
                .mapToInt(Product::getPrice)
                .min()
                .orElse(0);
        return !(price<minPrice);
    }

}
